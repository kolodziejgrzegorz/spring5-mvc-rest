package guru.spring.mvcrest.services;

import guru.spring.mvcrest.api.v1.mapper.VendorMapper;
import guru.spring.mvcrest.api.v1.model.VendorDTO;
import guru.spring.mvcrest.api.v1.model.VendorListDTO;
import guru.spring.mvcrest.controllers.v1.VendorController;
import guru.spring.mvcrest.domain.Vendor;
import guru.spring.mvcrest.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class VendorServiceTest {

    public static final String NAME = "Name";
    public static final Long ID = 2L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void getAllVendors() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        VendorListDTO vendorDTOS = vendorService.getAllVendors();

        assertEquals(3, vendorDTOS.getVendors().size());
    }

    @Test
    public void getVendorById() {
//        //given
//        Vendor vendor1 = new Vendor();
//        vendor1.setId(1l);
//        vendor1.setName(NAME);
//
//        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(vendor1));
//
//        //when
//        VendorDTO vendorDTO = vendorService.getVendorById(1L);
//
//        assertEquals(NAME, vendorDTO.getName());

        Vendor vendor = getVendor();
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(1L);
        then(vendorRepository).should(times(1)).findById(anyLong());

        assertThat(vendorDTO.getName(), is(equalTo(NAME)));
    }

    @Test
    public void createNewVendor() {

        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName(NAME);

        Vendor savedVendor = getVendor();
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDto = vendorService.createNewVendor(vendor1);

        assertEquals(vendor1.getName(), savedDto.getName());
        assertThat(savedDto.getVendorUrl(), containsString("1"));
    }

    @Test
    public void saveVendorByDTO() {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName(NAME);

        Vendor savedVendor = getVendor();
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDto = vendorService.saveVendorByDTO(1L, vendor1);

        assertEquals(vendor1.getName(), savedDto.getName());
        assertEquals(VendorController.BASE_URL + "/1" , savedDto.getVendorUrl());
    }

    @Test
    public void deleteVendorById(){

        Long id = 1L;
        vendorService.deleteById(id);

        verify(vendorRepository).deleteById(anyLong());
    }

    private Vendor getVendor(){
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        return vendor;
    }
}
