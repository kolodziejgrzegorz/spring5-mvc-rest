package guru.spring.mvcrest.api.v1.mapper;

import guru.spring.mvcrest.api.v1.model.CategoryDTO;
import guru.spring.mvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
