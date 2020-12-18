package com.example.Study.service;

import com.example.Study.model.entity.Item;
import com.example.Study.model.network.Header;
import com.example.Study.model.network.request.ItemApiRequest;
import com.example.Study.model.network.response.ItemApiResponse;
import com.example.Study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
//public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

//    @Autowired
//    private ItemRepository itemRepository;
    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        ItemApiRequest data = request.getData();

        Item item = Item.builder()
                .status(data.getStatus())
                .name(data.getName())
                .title(data.getTitle())
                .content(data.getContent())
                .price(data.getPrice())
                .brandName(data.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(data.getPartnerId()))
                .build();

        Item newItem = baseRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

//        Optional<Item> optional = itemRepository.findById(id);
//        return optional
//                .map(item -> response(item))
//                .orElseGet(() -> Header.ERROR("NONE DATA"));

        return baseRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

        // 1.Data
        ItemApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(oldItem -> {
                    oldItem
                            .setStatus(body.getStatus())
                            .setName(body.getName())
                            .setTitle(body.getTitle())
                            .setContent(body.getContent())
                            .setPrice(body.getPrice())
                            .setBrandName(body.getBrandName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                            ;

                    return oldItem;
                })
                .map(newItem -> baseRepository.save(newItem))
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header delete(Long id) {

        return baseRepository.findById(id)
                .map(item -> {
                    baseRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("NONE DATE"));
    }

    public Header<ItemApiResponse> response(Item item){
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(itemApiResponse);
    }
}
