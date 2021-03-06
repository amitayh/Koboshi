package com.wix.hoopoe.koboshi.cache.defaults

import java.io.File

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.wix.hoopoe.koboshi.cache.persistence.FolderPersistentCaches
import com.wix.hoopoe.koboshi.cache.{CustomizableResilientCaches, ResilientCaches}
import com.wix.hoopoe.koboshi.marshaller.JacksonMarshallers
import com.wix.hoopoe.koboshi.registry.MapBasedRemoteDataFetcherRegistry
import com.wix.hoopoe.koboshi.report.SLF4JReporters
import com.wix.hoopoe.koboshi.scheduler.{JavaUtilExecutorsSchedulers, SystemClock}

//TODO test this- adding as is for easy on-boarding. need to pay back test once time config is exposed
object ResilientCaches {
  def resilientCaches(cacheFolder: File): ResilientCaches = {
    new CustomizableResilientCaches(
      new MapBasedRemoteDataFetcherRegistry,
      new JavaUtilExecutorsSchedulers,
      new SLF4JReporters,
      new FolderPersistentCaches(cacheFolder),
      new JacksonMarshallers(new ObjectMapper().registerModule(DefaultScalaModule).registerModule(new JodaModule)),
      new SystemClock
    )
  }
}
