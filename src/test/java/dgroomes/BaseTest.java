package dgroomes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/**
 * Consider extending new test classes from this class.
 */
public abstract class BaseTest {

    ObjectMapper mapper = new ObjectMapper();
    ObjectMapper mapperParamNames = new ObjectMapper().registerModule(new ParameterNamesModule());
    ObjectMapper mapperParamNamesSnakeCase = new ObjectMapper().registerModule(new ParameterNamesModule()).setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    CsvMapper csvMapper = new CsvMapper();
}
