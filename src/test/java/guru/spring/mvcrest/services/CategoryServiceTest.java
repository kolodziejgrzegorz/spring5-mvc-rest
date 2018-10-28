package guru.spring.mvcrest.services;

import guru.spring.mvcrest.api.v1.mapper.CategoryMapper;
import guru.spring.mvcrest.api.v1.model.CategoryDTO;
import guru.spring.mvcrest.domain.Category;
import guru.spring.mvcrest.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    public static final String NAME = "Joe";
    public static final Long ID = 2L;

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategories() {

        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        assertEquals(3, categoryDTOS.size());
    }

    @Test
    public void getCategoryByName() {

        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findByName(any())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        assertEquals(NAME, categoryDTO.getName());
        assertEquals(ID, categoryDTO.getId());
    }
}