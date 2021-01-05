package com.st.recommender.db.mapper.dy;

import com.st.recommender.db.entity.dy.DYData;
import com.st.recommender.db.entity.dy.DYDataExample;
import com.st.recommender.db.entity.dy.DYDataWithBLOBs;
import java.util.List;

public interface DYDataMapper {
    long countByExample(DYDataExample example);

    List<DYDataWithBLOBs> selectByExampleWithBLOBs(DYDataExample example);

    List<DYData> selectByExample(DYDataExample example);
}