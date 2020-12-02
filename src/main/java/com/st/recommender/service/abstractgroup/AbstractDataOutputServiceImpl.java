package com.st.recommender.service.abstractgroup;

import com.st.recommender.model.input.Param;
import com.st.recommender.service.DataOutputService;
import com.st.recommender.service.FileOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public abstract class AbstractDataOutputServiceImpl extends AbstractDataProcessorImpl implements DataOutputService {
    @Autowired
    protected FileOperationService fileOperationService;

    public <T> String outputAsFile(Param param, T data) {
        Param funcParam = configConverter().apply(param);
        String filePath = filePathConverter().apply(param);
        Object workData = dataConverter().apply(data);
        
        return doWriteFile(filePath, funcParam, workData);
    }

    protected abstract String doWriteFile(String filePath, Param funcParam, Object workData);

    protected abstract Function<Param, String> filePathConverter();

    protected abstract Function<Param, ? extends Param> configConverter();

    protected abstract <T, R> Function<T, R> dataConverter();

}
