//package edu.stonybrook.redistricting.lemonkeredistricting.config;
//
//import edu.stonybrook.redistricting.lemonkeredistricting.batchprocessing.DistrictingProcessor;
//import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
//import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingSummary;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.*;
//import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
//import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
//import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
//import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.Resource;
//
//import javax.persistence.EntityManagerFactory;
//
//
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfiguration {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    private Resource outputResource = new FileSystemResource("output/outputData.csv");
//
//    @Bean
//    public JpaPagingItemReader<Districting> reader(EntityManagerFactory entityManagerFactory) {
//
//        JpaNativeQueryProvider<Districting> queryProvider = new JpaNativeQueryProvider<>();
//        queryProvider.setSqlQuery("select * from districting");
//        queryProvider.setEntityClass(Districting.class);
//
//        return new JpaPagingItemReaderBuilder<Districting>()
//                .name("creditReader")
//                .entityManagerFactory(entityManagerFactory)
//                .queryProvider(queryProvider)
//                .pageSize(1000)
//                .build();
//
////        JpaCursorItemReader<Districting> reader = new JpaCursorItemReader<>();
////        reader.setQueryProvider("select * from districting");
////        return reader;
//    }
//
//    @Bean
//    public DistrictingProcessor processor() {
//        return new DistrictingProcessor();
//    }
//
//    @Bean
//    public ItemWriter<DistrictingSummary> writer() {
//
//        //Create writer instance
//        FlatFileItemWriter<DistrictingSummary> writer = new FlatFileItemWriter<>();
//
//        //Set output file location
//        writer.setResource(outputResource);
//
//        //All job repetitions should "append" to same output file
//        writer.setAppendAllowed(true);
//
//        //Name field values sequence based on object properties
//        writer.setLineAggregator(new DelimitedLineAggregator<DistrictingSummary>() {
//            {
//                setDelimiter(",");
//                setFieldExtractor(new BeanWrapperFieldExtractor<DistrictingSummary>() {
//                    {
//                        setNames(new String[]{"id", "firstName", "lastName"});
//                    }
//                });
//            }
//        });
//        return writer;
//
//
////        return new JdbcBatchItemWriterBuilder<Districting>()
////                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
////                .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
////                .dataSource()
////                .build();
//    }
//
//    @Bean
//    public Job importUserJob(Step step1) {
//        return jobBuilderFactory.get("importUserJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(step1)
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1(JpaPagingItemReader<Districting> reader, FlatFileItemWriter<DistrictingSummary> writer) {
//        return stepBuilderFactory.get("step1")
//                .<Districting, DistrictingSummary>chunk(10)
//                .reader(reader)
//                .processor(processor())
//                .writer(writer)
//                .build();
//    }
//}
