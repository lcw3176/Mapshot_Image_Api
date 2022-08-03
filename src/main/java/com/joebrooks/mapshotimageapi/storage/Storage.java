package com.joebrooks.mapshotimageapi.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("storage")
public class Storage {

    @Id
    @Indexed
    private String uuid;

    private byte[] imageByte;

}
