package fpt.intern.fa_api.model.response.AsEntity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PermissionResponseList {

    private String name;
    private List<Object> list;

}
