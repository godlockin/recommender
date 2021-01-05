package com.st.recommender.db.mapper.tb;

import com.st.recommender.db.entity.tb.TBData;
import com.st.recommender.db.entity.tb.TBDataExample;
import com.st.recommender.db.entity.tb.TBDataWithBLOBs;
import java.util.List;

public interface TBDataMapper {
    long countByExample(TBDataExample example);

    List<TBDataWithBLOBs> selectByExampleWithBLOBs(TBDataExample example);

    List<TBData> selectByExample(TBDataExample example);
}