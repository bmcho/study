package com.example.Study.service;


import com.example.Study.model.entity.Category;
import com.example.Study.model.network.Header;
import com.example.Study.model.network.request.CategoryApiRequest;
import com.example.Study.model.network.response.CategoryApiResponse;
import org.springframework.stereotype.Service;

@Service
//public class CategoryApiLogicService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {

//    private CategoryRepository categoryRepository;

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {

        CategoryApiRequest body = request.getData();

        Category category = Category.builder()
                .title(body.getTitle())
                .type(body.getType()).build();

        Category newCategory = baseRepository.save(category);

        return response(newCategory);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(category -> response(category))
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {

        CategoryApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(category -> {
                    category
                            .setTitle(body.getTitle())
                            .setType(body.getType());

                    return baseRepository.save(category);
                })
                .map(category -> response(category))
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(category -> {
                    baseRepository.delete(category);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    private Header<CategoryApiResponse> response(Category category){
        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .type(category.getType())
                .title(category.getTitle()).build();

        return Header.OK(categoryApiResponse);
    }
}
