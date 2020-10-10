package com.andyartz.targregator.mongo

import org.springframework.data.repository.PagingAndSortingRepository

interface ProductRepository extends PagingAndSortingRepository<ProductRecord, BigInteger> {}
