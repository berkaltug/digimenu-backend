package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.TransferCartDto;
import com.digimenu.main.domain.request.TransferCartRequest;

public class TransferCartConverter {

    public static TransferCartDto convert(TransferCartRequest request){
        final TransferCartDto dto=new TransferCartDto();
        dto.setId(request.getId());
        dto.setSource(request.getSource());
        dto.setTarget(request.getTarget());
        return dto;
    }
}
