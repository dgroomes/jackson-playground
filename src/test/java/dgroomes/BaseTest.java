package dgroomes;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/**
 * Consider extending new test classes from this class.
 */
public abstract class BaseTest {

    JsonMapper mapper;
    JsonMapper mapperParamNames;
    CsvMapper csvMapper = new CsvMapper();

    {
        var builder = JsonMapper.builder().propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper = builder.build();
        mapperParamNames = builder.addModule(new ParameterNamesModule()).build();
    }
}
