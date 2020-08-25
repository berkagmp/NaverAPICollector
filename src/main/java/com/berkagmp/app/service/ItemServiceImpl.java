package com.berkagmp.app.service;

import com.berkagmp.app.entity.Product;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.berkagmp.app.entity.Item;
import com.berkagmp.app.entity.Result;
import com.berkagmp.app.repo.ItemRepository;
import com.google.gson.Gson;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

  @Value("${naver.client.id}")
  private String clientId;

  @Value("${naver.client.secret}")
  private String clientSecret;

  @Value("${naver.api_url}")
  private String _apiURL;

  @Value("${naver.web_url}")
  private String _webUrl;

  ItemRepository itemRepository;
  ProductService productService;

  @Autowired
  public ItemServiceImpl(ItemRepository itemRepository, ProductService productService) {
    this.itemRepository = itemRepository;
    this.productService = productService;
  }

  @Override
  public List<Item> list() {
    return itemRepository.findAll();
  }

  @Override
  public List<Item> listByActive(Boolean active) {
    return itemRepository.findByActive(active);
  }

  @Override
  public List<Item> listByStatusAndCreatedAt(Boolean status, String createdAt) {
    return itemRepository.findByStatusAndCreatedAt(status, createdAt);
  }

  @Override
  public Optional<Item> get(Long id) {
    return itemRepository.findById(id);
  }

  @Override
  public Item save(Item item) {
    return itemRepository.save(item);
  }

  @Override
  public Item update(Long itemId, String pId, String title, String mallName, String link,
      Integer lprice, Integer deliveryFee, Integer sum, Boolean active)
      throws NoSuchElementException {

    Item item = verifyItem(itemId);
    item.setPId(pId);
    item.setTitle(title);
    item.setMallName(mallName);
    item.setLink(link);
    item.setLprice(lprice);
    item.setDeliveryFee(deliveryFee);
    item.setSum(sum);
    item.setActive(active);

    return itemRepository.save(item);
  }

  @Override
  public Item updateSome(Long itemId, String pId, String title, String mallName, String link,
      Integer lprice, Integer deliveryFee, Integer sum, Boolean active)
      throws NoSuchElementException {

    Item item = verifyItem(itemId);

    if (pId != null) {
      item.setPId(pId);
    }

    if (title != null) {
      item.setTitle(title);
    }

    if (mallName != null) {
      item.setMallName(mallName);
    }

    if (link != null) {
      item.setLink(link);
    }

    if (lprice != null) {
      item.setLprice(lprice);
    }

    if (deliveryFee != null) {
      item.setDeliveryFee(deliveryFee);
    }

    if (sum != null) {
      item.setSum(sum);
    }

    if (active != null) {
      item.setActive(active);
    }

    return itemRepository.save(item);
  }

  @Override
  public void delete(Long itemId) {
    itemRepository.deleteById(itemId);
  }

  @Override
  public Item verifyItem(Long id) {
    return itemRepository.findById(id)
                         .orElseThrow(
                             () -> new NoSuchElementException("Item does not exist " + id));
  }

  @Override
  public Result collect(String keyword) {
    Gson gson = new Gson();
    Result result = null;

    BufferedReader br = null;
    boolean success = false;

    try {
      String text = URLEncoder.encode(keyword, "UTF-8");
      String apiURL = _apiURL + text;

      URL url = new URL(apiURL);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();

      con.setRequestMethod("GET");
      con.setRequestProperty("X-Naver-Client-Id", clientId);
      con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

      int responseCode = con.getResponseCode();

      if (responseCode == 200) { // Success
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
          response.append(inputLine);
        }

        result = gson.fromJson(response.toString(), Result.class);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  @Transactional
  public void collect() {
    List<Product> products = productService.listByActive(true);

    products.forEach(p -> {
      List<Item> items = collect(p.getKeyword()).getItems().stream().map(i -> {
        i.setProduct(p);
        return i;
      }).collect(Collectors.toList());

      items.forEach(i -> save(i));
    });
  }

}
