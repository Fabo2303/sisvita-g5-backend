package com.example.sisvita.config.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class AuthorityDeserializer extends JsonDeserializer {

    @Override
    public Object deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) arg0.getCodec();
        JsonNode JsonNode = mapper.readTree(arg0);
        List<GrantedAuthority> authorities = new LinkedList<>();
        Iterator<JsonNode> elements = JsonNode.elements();
        while (elements.hasNext()) {
            JsonNode next = elements.next();
            JsonNode authority = next.get("authority");
            authorities.add(new SimpleGrantedAuthority(authority.asText()));
        }
        return authorities;
    }

}
