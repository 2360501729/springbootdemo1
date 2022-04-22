package com.lcl.pname.controllerconfig;

import appcontext.AppConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MVCConfiguration implements WebMvcConfigurer {

    /**
     * 标准请求中的日期格式化配置:
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        final DateFormatter dateFormat = new DateFormatter();
        dateFormat.setFallbackPatterns(
                AppConstant.DEFAULT_DATE_FORMAT,
                AppConstant.DEFAULT_TIME_FORMAT,
                AppConstant.DEFAULT_DATETIME_PATTERN,
                "yyyy/MM/dd HH:mm:ss",
                "yyyy/MM/dd",
                "yyyy-MM-dd'T'HH:mm:ss");

        registry.addFormatter(dateFormat);

        /*配置新日期格式化*/
        final DateTimeFormatterRegistrar dateTimeFormatterRegistrar =
                new DateTimeFormatterRegistrar();
        dateTimeFormatterRegistrar.setDateFormatter(DateTimeFormatter.ofPattern(AppConstant.DEFAULT_DATE_FORMAT));
        dateTimeFormatterRegistrar.setTimeFormatter(DateTimeFormatter.ofPattern(AppConstant.DEFAULT_TIME_FORMAT));
        dateTimeFormatterRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(AppConstant.DEFAULT_DATETIME_PATTERN));
        dateTimeFormatterRegistrar.registerFormatters(registry);
    }

    /**
     * Json请求中的日期格式化配置:
     * 启动文件中配置的只对Date类型有效,JacksonAutoConfiguration类中configureDateFormat()有配置过程
     * 这里新增 新日期API配置
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        Stream<HttpMessageConverter<?>> httpMessageConverterStream =
                converters.stream().filter(v -> v instanceof MappingJackson2HttpMessageConverter)
                        .collect(Collectors.toList()).forEach(v->{
                            /*
                            //对Date类型日期处理,启动配置文件已经配置了,再写一遍加深印象
                            ObjectMapper objectMapper = new ObjectMapper();
                            //指定时区
                            objectMapper.setTimeZone(TimeZone.getTimeZone(AppConstant.TIME_ZONE));
                            objectMapper.setDateFormat(new SimpleDateFormat(AppConstant.DEFAULT_DATETIME_PATTERN));
                            //序列化策略:对不为 null 的进行序列化
                            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);*/

                    //新日期处理
                            JavaTimeModule javaTimeModule = new JavaTimeModule();
                            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer
                                    (DateTimeFormatter.ofPattern(AppConstant.DEFAULT_DATETIME_PATTERN)));
                            javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer
                                    (DateTimeFormatter.ofPattern(AppConstant.DEFAULT_DATE_FORMAT)));
                            javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer
                                    (DateTimeFormatter.ofPattern(AppConstant.DEFAULT_TIME_FORMAT)));
                        });
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    /**
     * 配置跨域策略
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    }
}
