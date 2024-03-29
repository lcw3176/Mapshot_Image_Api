package com.joebrooks.mapshotimageapi.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;



@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class StorageControllerTest {


    @Autowired
    private MockMvc mockMvc;
//
//    @SpyBean
//    private StorageManager storageManager;
//
//    @Test
//    void 이미지_반환() throws Exception {
//        Mockito.when(storageManager.popImage(Mockito.any()))
//                .thenReturn(java.util.Optional.of(new ByteArrayResource(new byte[100])));
//
//
//        mockMvc.perform(get("/map/storage/{uuid}", UUID.randomUUID())
//                ).andExpect(status().isOk())
//                 .andDo(
//                         document("get-done-image",
//                                 pathParameters(
//                                         parameterWithName("uuid").description("이미지 UUID")
//                                 )
//                         )
//                 );
//    }
}