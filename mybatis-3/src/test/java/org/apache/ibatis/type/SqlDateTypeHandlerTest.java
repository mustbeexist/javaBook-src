/**
 * Copyright 2009-2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.type;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SqlDateTypeHandlerTest extends BaseTypeHandlerTest {

    private static final TypeHandler<java.sql.Date> TYPE_HANDLER = new SqlDateTypeHandler();
    private static final java.sql.Date SQL_DATE = new java.sql.Date(new Date().getTime());

    @Override
    @Test
    public void shouldSetParameter() throws Exception {
        TYPE_HANDLER.setParameter(ps, 1, SQL_DATE, null);
        verify(ps).setDate(1, SQL_DATE);
    }

    @Override
    @Test
    public void shouldGetResultFromResultSetByName() throws Exception {
        when(rs.getDate("column")).thenReturn(SQL_DATE);
        assertEquals(SQL_DATE, TYPE_HANDLER.getResult(rs, "column"));
        verify(rs, never()).wasNull();
    }

    @Override
    public void shouldGetResultNullFromResultSetByName() throws Exception {
        // Unnecessary
    }

    @Override
    @Test
    public void shouldGetResultFromResultSetByPosition() throws Exception {
        when(rs.getDate(1)).thenReturn(SQL_DATE);
        assertEquals(SQL_DATE, TYPE_HANDLER.getResult(rs, 1));
        verify(rs, never()).wasNull();
    }

    @Override
    public void shouldGetResultNullFromResultSetByPosition() throws Exception {
        // Unnecessary
    }

    @Override
    @Test
    public void shouldGetResultFromCallableStatement() throws Exception {
        when(cs.getDate(1)).thenReturn(SQL_DATE);
        assertEquals(SQL_DATE, TYPE_HANDLER.getResult(cs, 1));
        verify(cs, never()).wasNull();
    }

    @Override
    public void shouldGetResultNullFromCallableStatement() throws Exception {
        // Unnecessary
    }

}