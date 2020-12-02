package com.st.recommender.model.opt.als;

import com.st.recommender.model.input.Param;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class AlsParam extends Param {
    private List<String> headers;

    public AlsParam(Param param) {
        BeanUtils.copyProperties(param, this);
    }
}
