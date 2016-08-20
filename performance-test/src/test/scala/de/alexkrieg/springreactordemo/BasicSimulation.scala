package de.alexkrieg.springreactordemo

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.util.Random

class BasicSimulation extends Simulation {

  val host = System.getProperty("host");
  val restport = System.getProperty("restport");
  val reactport = System.getProperty("reactport");

  val httpConf = http
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val httpConfRest = http.copy(httpConf).baseURL("http://"+host + ":" + restport)
  val httpConfReact = http.copy(httpConf).baseURL("http://"+host + ":" + reactport)
  

  val scnRestAll = scenario("Rest - all services") // 
    .exec(http("rest request_1")
      .get("/rest/"+Random.nextInt(50))
      .check(status.is(200)))
      
  val scnReactAll = scenario("React - all services") // 
    .exec(http("react request_1")
      .get("/react/"+Random.nextInt(50))
      .check(status.is(200)))
      
  val userInjection = rampUsersPerSec(10) to(80) during(2 minutes)
    setUp(scnRestAll.inject( userInjection).protocols(httpConfRest))
//      scnRestAll.inject(scenarioInj).protocols(httpConfRest))
}