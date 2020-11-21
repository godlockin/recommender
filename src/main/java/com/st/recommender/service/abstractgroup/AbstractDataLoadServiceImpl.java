package com.st.recommender.service.abstractgroup;

import com.st.recommender.model.input.Param;
import com.st.recommender.service.DataLoadService;
import com.st.recommender.service.FileLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public abstract class AbstractDataLoadServiceImpl extends AbstractDataProcessorImpl implements DataLoadService {
    @Autowired
    protected FileLoadService fileLoadService;

    public <T> List<T> loadData(Param param) {
        return fileLoadService.loadFileAsPojo(filePathConverter().apply(param), pojoClass());
    }

    protected abstract Function<Param, String> filePathConverter();

    protected abstract <T> Class<T> pojoClass();
}
