package guru.spring.mvcrest.controllers.v1;

import guru.spring.mvcrest.api.v1.model.CategoryDTO;
import guru.spring.mvcrest.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    public static final String NAME = "Joe";
    public static final Long ID = 2L;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllCategories() throws Exception {

        CategoryDTO category1 = new CategoryDTO();
        category1.setName("joe");
        category1.setId(1L);


        CategoryDTO category2 = new CategoryDTO();
        category2.setName("Jan");
        category2.setId(2L);

        List<CategoryDTO> categoryDTOS = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categoryDTOS);

        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void getCategoryByName() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setName(NAME);
        category1.setId(1L);

        when(categoryService.getCategoryByName(any())).thenReturn(category1);

        mockMvc.perform(get("/api/v1/categories/" + NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));

    }
}