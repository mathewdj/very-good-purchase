package green.seagull.very.good.purchase.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@EnableReactiveMongoRepositories
open class MongoReactiveConfiguration : AbstractReactiveMongoConfiguration() {

    @Bean
    open fun mongoClient(): MongoClient = MongoClients.create()

    override fun getDatabaseName(): String = "very_good_purchase_db"
}