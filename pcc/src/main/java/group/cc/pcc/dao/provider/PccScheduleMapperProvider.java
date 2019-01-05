package group.cc.pcc.dao.provider;

import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

public class PccScheduleMapperProvider extends MapperTemplate {

    public PccScheduleMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String dayCount() {
        return "select " +
                "count(*) as count, " +
                "DATE_FORMAT(s.create_time,'%Y%m%d') as create_time " +
                "from pcc_schedule as s " +
                "where " +
                "s.pcc_user_id=#{pccUserId} " +
                "AND " +
                "DATE_FORMAT(s.create_time,'%Y%m%d') >= ?" +
                "AND " +
                "DATE_FORMAT(s.create_time,'%Y%m%d') <= #{endDate} " +
                "GROUP BY DATE_FORMAT(s.create_time,'%Y%m%d')";
    }
}
