/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.headless.foundation.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import com.liferay.headless.foundation.dto.v1_0.TaxonomyVocabulary;
import com.liferay.headless.foundation.resource.v1_0.TaxonomyVocabularyResource;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;

import java.net.URL;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseTaxonomyVocabularyResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		_resourceURL = new URL(
			"http://localhost:8080/o/headless-foundation/v1.0");
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testGetContentSpaceTaxonomyVocabulariesPage() throws Exception {
		Long contentSpaceId =
			testGetContentSpaceTaxonomyVocabulariesPage_getContentSpaceId();
		Long irrelevantContentSpaceId =
			testGetContentSpaceTaxonomyVocabulariesPage_getIrrelevantContentSpaceId();

		if ((irrelevantContentSpaceId != null)) {
			TaxonomyVocabulary irrelevantTaxonomyVocabulary =
				testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
					irrelevantContentSpaceId,
					randomIrrelevantTaxonomyVocabulary());

			Page<TaxonomyVocabulary> page =
				invokeGetContentSpaceTaxonomyVocabulariesPage(
					irrelevantContentSpaceId, null, Pagination.of(1, 2), null);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantTaxonomyVocabulary),
				(List<TaxonomyVocabulary>)page.getItems());
			assertValid(page);
		}

		TaxonomyVocabulary taxonomyVocabulary1 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, randomTaxonomyVocabulary());

		TaxonomyVocabulary taxonomyVocabulary2 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, randomTaxonomyVocabulary());

		Page<TaxonomyVocabulary> page =
			invokeGetContentSpaceTaxonomyVocabulariesPage(
				contentSpaceId, null, Pagination.of(1, 2), null);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(taxonomyVocabulary1, taxonomyVocabulary2),
			(List<TaxonomyVocabulary>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetContentSpaceTaxonomyVocabulariesPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Long contentSpaceId =
			testGetContentSpaceTaxonomyVocabulariesPage_getContentSpaceId();

		TaxonomyVocabulary taxonomyVocabulary1 = randomTaxonomyVocabulary();
		TaxonomyVocabulary taxonomyVocabulary2 = randomTaxonomyVocabulary();

		for (EntityField entityField : entityFields) {
			BeanUtils.setProperty(
				taxonomyVocabulary1, entityField.getName(),
				DateUtils.addMinutes(new Date(), -2));
		}

		taxonomyVocabulary1 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, taxonomyVocabulary1);

		Thread.sleep(1000);

		taxonomyVocabulary2 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, taxonomyVocabulary2);

		for (EntityField entityField : entityFields) {
			Page<TaxonomyVocabulary> page =
				invokeGetContentSpaceTaxonomyVocabulariesPage(
					contentSpaceId,
					getFilterString(entityField, "eq", taxonomyVocabulary1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(taxonomyVocabulary1),
				(List<TaxonomyVocabulary>)page.getItems());
		}
	}

	@Test
	public void testGetContentSpaceTaxonomyVocabulariesPageWithFilterStringEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Long contentSpaceId =
			testGetContentSpaceTaxonomyVocabulariesPage_getContentSpaceId();

		TaxonomyVocabulary taxonomyVocabulary1 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, randomTaxonomyVocabulary());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		TaxonomyVocabulary taxonomyVocabulary2 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, randomTaxonomyVocabulary());

		for (EntityField entityField : entityFields) {
			Page<TaxonomyVocabulary> page =
				invokeGetContentSpaceTaxonomyVocabulariesPage(
					contentSpaceId,
					getFilterString(entityField, "eq", taxonomyVocabulary1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(taxonomyVocabulary1),
				(List<TaxonomyVocabulary>)page.getItems());
		}
	}

	@Test
	public void testGetContentSpaceTaxonomyVocabulariesPageWithPagination()
		throws Exception {

		Long contentSpaceId =
			testGetContentSpaceTaxonomyVocabulariesPage_getContentSpaceId();

		TaxonomyVocabulary taxonomyVocabulary1 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, randomTaxonomyVocabulary());

		TaxonomyVocabulary taxonomyVocabulary2 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, randomTaxonomyVocabulary());

		TaxonomyVocabulary taxonomyVocabulary3 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, randomTaxonomyVocabulary());

		Page<TaxonomyVocabulary> page1 =
			invokeGetContentSpaceTaxonomyVocabulariesPage(
				contentSpaceId, null, Pagination.of(1, 2), null);

		List<TaxonomyVocabulary> taxonomyVocabularies1 =
			(List<TaxonomyVocabulary>)page1.getItems();

		Assert.assertEquals(
			taxonomyVocabularies1.toString(), 2, taxonomyVocabularies1.size());

		Page<TaxonomyVocabulary> page2 =
			invokeGetContentSpaceTaxonomyVocabulariesPage(
				contentSpaceId, null, Pagination.of(2, 2), null);

		Assert.assertEquals(3, page2.getTotalCount());

		List<TaxonomyVocabulary> taxonomyVocabularies2 =
			(List<TaxonomyVocabulary>)page2.getItems();

		Assert.assertEquals(
			taxonomyVocabularies2.toString(), 1, taxonomyVocabularies2.size());

		assertEqualsIgnoringOrder(
			Arrays.asList(
				taxonomyVocabulary1, taxonomyVocabulary2, taxonomyVocabulary3),
			new ArrayList<TaxonomyVocabulary>() {
				{
					addAll(taxonomyVocabularies1);
					addAll(taxonomyVocabularies2);
				}
			});
	}

	@Test
	public void testGetContentSpaceTaxonomyVocabulariesPageWithSortDateTime()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Long contentSpaceId =
			testGetContentSpaceTaxonomyVocabulariesPage_getContentSpaceId();

		TaxonomyVocabulary taxonomyVocabulary1 = randomTaxonomyVocabulary();
		TaxonomyVocabulary taxonomyVocabulary2 = randomTaxonomyVocabulary();

		for (EntityField entityField : entityFields) {
			BeanUtils.setProperty(
				taxonomyVocabulary1, entityField.getName(),
				DateUtils.addMinutes(new Date(), -2));
		}

		taxonomyVocabulary1 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, taxonomyVocabulary1);

		Thread.sleep(1000);

		taxonomyVocabulary2 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, taxonomyVocabulary2);

		for (EntityField entityField : entityFields) {
			Page<TaxonomyVocabulary> ascPage =
				invokeGetContentSpaceTaxonomyVocabulariesPage(
					contentSpaceId, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(taxonomyVocabulary1, taxonomyVocabulary2),
				(List<TaxonomyVocabulary>)ascPage.getItems());

			Page<TaxonomyVocabulary> descPage =
				invokeGetContentSpaceTaxonomyVocabulariesPage(
					contentSpaceId, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(taxonomyVocabulary2, taxonomyVocabulary1),
				(List<TaxonomyVocabulary>)descPage.getItems());
		}
	}

	@Test
	public void testGetContentSpaceTaxonomyVocabulariesPageWithSortString()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Long contentSpaceId =
			testGetContentSpaceTaxonomyVocabulariesPage_getContentSpaceId();

		TaxonomyVocabulary taxonomyVocabulary1 = randomTaxonomyVocabulary();
		TaxonomyVocabulary taxonomyVocabulary2 = randomTaxonomyVocabulary();

		for (EntityField entityField : entityFields) {
			BeanUtils.setProperty(
				taxonomyVocabulary1, entityField.getName(), "Aaa");
			BeanUtils.setProperty(
				taxonomyVocabulary2, entityField.getName(), "Bbb");
		}

		taxonomyVocabulary1 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, taxonomyVocabulary1);

		taxonomyVocabulary2 =
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				contentSpaceId, taxonomyVocabulary2);

		for (EntityField entityField : entityFields) {
			Page<TaxonomyVocabulary> ascPage =
				invokeGetContentSpaceTaxonomyVocabulariesPage(
					contentSpaceId, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(taxonomyVocabulary1, taxonomyVocabulary2),
				(List<TaxonomyVocabulary>)ascPage.getItems());

			Page<TaxonomyVocabulary> descPage =
				invokeGetContentSpaceTaxonomyVocabulariesPage(
					contentSpaceId, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(taxonomyVocabulary2, taxonomyVocabulary1),
				(List<TaxonomyVocabulary>)descPage.getItems());
		}
	}

	protected TaxonomyVocabulary
			testGetContentSpaceTaxonomyVocabulariesPage_addTaxonomyVocabulary(
				Long contentSpaceId, TaxonomyVocabulary taxonomyVocabulary)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetContentSpaceTaxonomyVocabulariesPage_getContentSpaceId()
		throws Exception {

		return testGroup.getGroupId();
	}

	protected Long
			testGetContentSpaceTaxonomyVocabulariesPage_getIrrelevantContentSpaceId()
		throws Exception {

		return irrelevantGroup.getGroupId();
	}

	protected Page<TaxonomyVocabulary>
			invokeGetContentSpaceTaxonomyVocabulariesPage(
				Long contentSpaceId, String filterString, Pagination pagination,
				String sortString)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/content-spaces/{content-space-id}/taxonomy-vocabularies",
					contentSpaceId);

		location = HttpUtil.addParameter(location, "filter", filterString);

		location = HttpUtil.addParameter(
			location, "page", pagination.getPage());
		location = HttpUtil.addParameter(
			location, "pageSize", pagination.getPageSize());

		location = HttpUtil.addParameter(location, "sort", sortString);

		options.setLocation(location);

		String string = HttpUtil.URLtoString(options);

		return _outputObjectMapper.readValue(
			string,
			new TypeReference<Page<TaxonomyVocabulary>>() {
			});
	}

	protected Http.Response
			invokeGetContentSpaceTaxonomyVocabulariesPageResponse(
				Long contentSpaceId, String filterString, Pagination pagination,
				String sortString)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/content-spaces/{content-space-id}/taxonomy-vocabularies",
					contentSpaceId);

		location = HttpUtil.addParameter(location, "filter", filterString);

		location = HttpUtil.addParameter(
			location, "page", pagination.getPage());
		location = HttpUtil.addParameter(
			location, "pageSize", pagination.getPageSize());

		location = HttpUtil.addParameter(location, "sort", sortString);

		options.setLocation(location);

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	@Test
	public void testPostContentSpaceTaxonomyVocabulary() throws Exception {
		TaxonomyVocabulary randomTaxonomyVocabulary =
			randomTaxonomyVocabulary();

		TaxonomyVocabulary postTaxonomyVocabulary =
			testPostContentSpaceTaxonomyVocabulary_addTaxonomyVocabulary(
				randomTaxonomyVocabulary);

		assertEquals(randomTaxonomyVocabulary, postTaxonomyVocabulary);
		assertValid(postTaxonomyVocabulary);
	}

	protected TaxonomyVocabulary
			testPostContentSpaceTaxonomyVocabulary_addTaxonomyVocabulary(
				TaxonomyVocabulary taxonomyVocabulary)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected TaxonomyVocabulary invokePostContentSpaceTaxonomyVocabulary(
			Long contentSpaceId, TaxonomyVocabulary taxonomyVocabulary)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(taxonomyVocabulary),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		String location =
			_resourceURL +
				_toPath(
					"/content-spaces/{content-space-id}/taxonomy-vocabularies",
					contentSpaceId);

		options.setLocation(location);

		options.setPost(true);

		String string = HttpUtil.URLtoString(options);

		try {
			return _outputObjectMapper.readValue(
				string, TaxonomyVocabulary.class);
		}
		catch (Exception e) {
			Assert.fail("HTTP response: " + string);

			throw e;
		}
	}

	protected Http.Response invokePostContentSpaceTaxonomyVocabularyResponse(
			Long contentSpaceId, TaxonomyVocabulary taxonomyVocabulary)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(taxonomyVocabulary),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		String location =
			_resourceURL +
				_toPath(
					"/content-spaces/{content-space-id}/taxonomy-vocabularies",
					contentSpaceId);

		options.setLocation(location);

		options.setPost(true);

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	@Test
	public void testDeleteTaxonomyVocabulary() throws Exception {
		TaxonomyVocabulary taxonomyVocabulary =
			testDeleteTaxonomyVocabulary_addTaxonomyVocabulary();

		assertResponseCode(
			200,
			invokeDeleteTaxonomyVocabularyResponse(taxonomyVocabulary.getId()));

		assertResponseCode(
			404,
			invokeGetTaxonomyVocabularyResponse(taxonomyVocabulary.getId()));
	}

	protected TaxonomyVocabulary
			testDeleteTaxonomyVocabulary_addTaxonomyVocabulary()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected boolean invokeDeleteTaxonomyVocabulary(Long taxonomyVocabularyId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setDelete(true);

		String location =
			_resourceURL +
				_toPath(
					"/taxonomy-vocabularies/{taxonomy-vocabulary-id}",
					taxonomyVocabularyId);

		options.setLocation(location);

		String string = HttpUtil.URLtoString(options);

		try {
			return _outputObjectMapper.readValue(string, Boolean.class);
		}
		catch (Exception e) {
			Assert.fail("HTTP response: " + string);

			throw e;
		}
	}

	protected Http.Response invokeDeleteTaxonomyVocabularyResponse(
			Long taxonomyVocabularyId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setDelete(true);

		String location =
			_resourceURL +
				_toPath(
					"/taxonomy-vocabularies/{taxonomy-vocabulary-id}",
					taxonomyVocabularyId);

		options.setLocation(location);

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	@Test
	public void testGetTaxonomyVocabulary() throws Exception {
		TaxonomyVocabulary postTaxonomyVocabulary =
			testGetTaxonomyVocabulary_addTaxonomyVocabulary();

		TaxonomyVocabulary getTaxonomyVocabulary = invokeGetTaxonomyVocabulary(
			postTaxonomyVocabulary.getId());

		assertEquals(postTaxonomyVocabulary, getTaxonomyVocabulary);
		assertValid(getTaxonomyVocabulary);
	}

	protected TaxonomyVocabulary
			testGetTaxonomyVocabulary_addTaxonomyVocabulary()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected TaxonomyVocabulary invokeGetTaxonomyVocabulary(
			Long taxonomyVocabularyId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/taxonomy-vocabularies/{taxonomy-vocabulary-id}",
					taxonomyVocabularyId);

		options.setLocation(location);

		String string = HttpUtil.URLtoString(options);

		try {
			return _outputObjectMapper.readValue(
				string, TaxonomyVocabulary.class);
		}
		catch (Exception e) {
			Assert.fail("HTTP response: " + string);

			throw e;
		}
	}

	protected Http.Response invokeGetTaxonomyVocabularyResponse(
			Long taxonomyVocabularyId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/taxonomy-vocabularies/{taxonomy-vocabulary-id}",
					taxonomyVocabularyId);

		options.setLocation(location);

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	@Test
	public void testPutTaxonomyVocabulary() throws Exception {
		TaxonomyVocabulary postTaxonomyVocabulary =
			testPutTaxonomyVocabulary_addTaxonomyVocabulary();

		TaxonomyVocabulary randomTaxonomyVocabulary =
			randomTaxonomyVocabulary();

		TaxonomyVocabulary putTaxonomyVocabulary = invokePutTaxonomyVocabulary(
			postTaxonomyVocabulary.getId(), randomTaxonomyVocabulary);

		assertEquals(randomTaxonomyVocabulary, putTaxonomyVocabulary);
		assertValid(putTaxonomyVocabulary);

		TaxonomyVocabulary getTaxonomyVocabulary = invokeGetTaxonomyVocabulary(
			putTaxonomyVocabulary.getId());

		assertEquals(randomTaxonomyVocabulary, getTaxonomyVocabulary);
		assertValid(getTaxonomyVocabulary);
	}

	protected TaxonomyVocabulary
			testPutTaxonomyVocabulary_addTaxonomyVocabulary()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected TaxonomyVocabulary invokePutTaxonomyVocabulary(
			Long taxonomyVocabularyId, TaxonomyVocabulary taxonomyVocabulary)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(taxonomyVocabulary),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		String location =
			_resourceURL +
				_toPath(
					"/taxonomy-vocabularies/{taxonomy-vocabulary-id}",
					taxonomyVocabularyId);

		options.setLocation(location);

		options.setPut(true);

		String string = HttpUtil.URLtoString(options);

		try {
			return _outputObjectMapper.readValue(
				string, TaxonomyVocabulary.class);
		}
		catch (Exception e) {
			Assert.fail("HTTP response: " + string);

			throw e;
		}
	}

	protected Http.Response invokePutTaxonomyVocabularyResponse(
			Long taxonomyVocabularyId, TaxonomyVocabulary taxonomyVocabulary)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(taxonomyVocabulary),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		String location =
			_resourceURL +
				_toPath(
					"/taxonomy-vocabularies/{taxonomy-vocabulary-id}",
					taxonomyVocabularyId);

		options.setLocation(location);

		options.setPut(true);

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected void assertResponseCode(
		int expectedResponseCode, Http.Response actualResponse) {

		Assert.assertEquals(
			expectedResponseCode, actualResponse.getResponseCode());
	}

	protected void assertEquals(
		TaxonomyVocabulary taxonomyVocabulary1,
		TaxonomyVocabulary taxonomyVocabulary2) {

		Assert.assertTrue(
			taxonomyVocabulary1 + " does not equal " + taxonomyVocabulary2,
			equals(taxonomyVocabulary1, taxonomyVocabulary2));
	}

	protected void assertEquals(
		List<TaxonomyVocabulary> taxonomyVocabularies1,
		List<TaxonomyVocabulary> taxonomyVocabularies2) {

		Assert.assertEquals(
			taxonomyVocabularies1.size(), taxonomyVocabularies2.size());

		for (int i = 0; i < taxonomyVocabularies1.size(); i++) {
			TaxonomyVocabulary taxonomyVocabulary1 = taxonomyVocabularies1.get(
				i);
			TaxonomyVocabulary taxonomyVocabulary2 = taxonomyVocabularies2.get(
				i);

			assertEquals(taxonomyVocabulary1, taxonomyVocabulary2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<TaxonomyVocabulary> taxonomyVocabularies1,
		List<TaxonomyVocabulary> taxonomyVocabularies2) {

		Assert.assertEquals(
			taxonomyVocabularies1.size(), taxonomyVocabularies2.size());

		for (TaxonomyVocabulary taxonomyVocabulary1 : taxonomyVocabularies1) {
			boolean contains = false;

			for (TaxonomyVocabulary taxonomyVocabulary2 :
					taxonomyVocabularies2) {

				if (equals(taxonomyVocabulary1, taxonomyVocabulary2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				taxonomyVocabularies2 + " does not contain " +
					taxonomyVocabulary1,
				contains);
		}
	}

	protected void assertValid(TaxonomyVocabulary taxonomyVocabulary) {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertValid(Page<TaxonomyVocabulary> page) {
		boolean valid = false;

		Collection<TaxonomyVocabulary> taxonomyVocabularies = page.getItems();

		int size = taxonomyVocabularies.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected boolean equals(
		TaxonomyVocabulary taxonomyVocabulary1,
		TaxonomyVocabulary taxonomyVocabulary2) {

		if (taxonomyVocabulary1 == taxonomyVocabulary2) {
			return true;
		}

		return false;
	}

	protected Collection<EntityField> getEntityFields() throws Exception {
		if (!(_taxonomyVocabularyResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_taxonomyVocabularyResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField -> Objects.equals(entityField.getType(), type)
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator,
		TaxonomyVocabulary taxonomyVocabulary) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("assetTypes")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("availableLanguages")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("contentSpaceId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("creator")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("dateCreated")) {
			sb.append(_dateFormat.format(taxonomyVocabulary.getDateCreated()));

			return sb.toString();
		}

		if (entityFieldName.equals("dateModified")) {
			sb.append(_dateFormat.format(taxonomyVocabulary.getDateModified()));

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			sb.append("'");
			sb.append(String.valueOf(taxonomyVocabulary.getDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(taxonomyVocabulary.getName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("numberOfTaxonomyCategories")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("viewableBy")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected TaxonomyVocabulary randomTaxonomyVocabulary() {
		return new TaxonomyVocabulary() {
			{
				contentSpaceId = RandomTestUtil.randomLong();
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				description = RandomTestUtil.randomString();
				id = RandomTestUtil.randomLong();
				name = RandomTestUtil.randomString();
			}
		};
	}

	protected TaxonomyVocabulary randomIrrelevantTaxonomyVocabulary() {
		return randomTaxonomyVocabulary();
	}

	protected TaxonomyVocabulary randomPatchTaxonomyVocabulary() {
		return randomTaxonomyVocabulary();
	}

	protected Group irrelevantGroup;
	protected Group testGroup;

	protected static class Page<T> {

		public Collection<T> getItems() {
			return new ArrayList<>(items);
		}

		public long getLastPage() {
			return lastPage;
		}

		public long getPage() {
			return page;
		}

		public long getPageSize() {
			return pageSize;
		}

		public long getTotalCount() {
			return totalCount;
		}

		@JsonProperty
		protected Collection<T> items;

		@JsonProperty
		protected long lastPage;

		@JsonProperty
		protected long page;

		@JsonProperty
		protected long pageSize;

		@JsonProperty
		protected long totalCount;

	}

	private Http.Options _createHttpOptions() {
		Http.Options options = new Http.Options();

		options.addHeader("Accept", "application/json");

		String userNameAndPassword = "test@liferay.com:test";

		String encodedUserNameAndPassword = Base64.encode(
			userNameAndPassword.getBytes());

		options.addHeader(
			"Authorization", "Basic " + encodedUserNameAndPassword);

		options.addHeader("Content-Type", "application/json");

		return options;
	}

	private String _toPath(String template, Object... values) {
		if (ArrayUtil.isEmpty(values)) {
			return template;
		}

		for (int i = 0; i < values.length; i++) {
			template = template.replaceFirst(
				"\\{.*\\}", String.valueOf(values[i]));
		}

		return template;
	}

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;
	private final static ObjectMapper _inputObjectMapper = new ObjectMapper() {
		{
			setFilterProvider(
				new SimpleFilterProvider() {
					{
						addFilter(
							"Liferay.Vulcan",
							SimpleBeanPropertyFilter.serializeAll());
					}
				});
			setSerializationInclusion(JsonInclude.Include.NON_NULL);
		}
	};
	private final static ObjectMapper _outputObjectMapper = new ObjectMapper() {
		{
			setFilterProvider(
				new SimpleFilterProvider() {
					{
						addFilter(
							"Liferay.Vulcan",
							SimpleBeanPropertyFilter.serializeAll());
					}
				});
		}
	};

	@Inject
	private TaxonomyVocabularyResource _taxonomyVocabularyResource;

	private URL _resourceURL;

}