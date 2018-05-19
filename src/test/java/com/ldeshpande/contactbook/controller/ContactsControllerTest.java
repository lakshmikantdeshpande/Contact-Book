package com.ldeshpande.contactbook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ldeshpande.contactbook.model.Address;
import com.ldeshpande.contactbook.model.Contact;
import com.ldeshpande.contactbook.service.ContactService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class ContactsControllerTest {

    @Value("${basicAuth.username}")
    private String username;

    @Value("${basicAuth.tpassword}")
    private String password;

    @Autowired
    private WebApplicationContext context;

    @Mock
    private ContactService contactService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Contact contact;

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        System.out.println("hey");
    }

    @Before
    public void setup() {
        contact = new Contact();
        contact.setName("ABC");
        contact.setEmail("abc@def.ghi");
        contact.setPhoneNumber(999999999L);
        contact.setAddress(new Address());
    }

    @Test
    public void findContactByEmail() throws Exception {
        Mockito.when(contactService.findContactByEmail("abc@def.ghi")).thenReturn(contact);
        MvcResult result = mockMvc.perform(get("/api/v1/contacts/search?by=email&query=abc@def.ghi").with(httpBasic(username, password))).andExpect(status().isOk()).andReturn();
        Assert.assertTrue(result.getResponse().getContentAsString().contains("abc@def.ghi"));
    }

    @Test
    public void findContactByName() throws Exception {
        Mockito.when(contactService.findContactsByName("ABC")).thenReturn(Collections.singletonList(contact));
        MvcResult result = mockMvc.perform(get("/api/v1/contacts/search?by=name&query=ABC").with(httpBasic(username, password))).andExpect(status().isOk()).andReturn();
        Assert.assertTrue(result.getResponse().getContentAsString().contains("ABC"));
    }

    @Test
    public void addContact() throws Exception {
        Mockito.when(contactService.addContact(contact)).thenReturn(contact);
        String contactValue = objectMapper.writeValueAsString(contact);
        MvcResult result = mockMvc.perform(post("/api/v1/contacts").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(contactValue).with(httpBasic(username, password))).andExpect(status().isOk()).andReturn();
        Assert.assertTrue(result.getResponse().getContentAsString().contains("ABC"));
    }

    @Test
    public void deleteContact() throws Exception {
        Mockito.doNothing().when(contactService).deleteContact("abc@def.ghi");
        mockMvc.perform(delete("/api/v1/contacts")).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void updateContact() throws Exception {
        Mockito.when(contactService.updateContact(contact)).thenReturn(contact);
        String contactValue = objectMapper.writeValueAsString(contact);
        mockMvc.perform(put("/api/v1/contacts").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(contactValue).with(httpBasic(username, password))).andExpect(status().isOk()).andReturn();
    }
}