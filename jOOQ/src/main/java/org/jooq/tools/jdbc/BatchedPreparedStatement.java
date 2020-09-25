/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.tools.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A batched statement.
 * <p>
 * This statement doesn't execute immediately, but buffers all bind variables in
 * batch, delaying execution until a new SQL string is encountered. See
 * {@link BatchedConnection} for details.
 *
 * @author Lukas Eder
 * @see BatchedConnection
 */
public class BatchedPreparedStatement extends DefaultPreparedStatement {

    private int batches;

    public BatchedPreparedStatement(BatchedConnection connection, PreparedStatement delegate) {
        super(delegate, connection);
    }

    // -------------------------------------------------------------------------
    // XXX: Utilities
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // XXX: Executing queries
    // -------------------------------------------------------------------------

    @Override
    public int executeUpdate() throws SQLException {
        addBatch();
        return 0;
    }

    @Override
    public boolean execute() throws SQLException {
        addBatch();
        return false;
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return 0;
    }



    @Override
    public long executeLargeUpdate() throws SQLException {
        return executeUpdate();
    }

    @Override
    public long getLargeUpdateCount() throws SQLException {
        return getUpdateCount();
    }



    @Override
    public void close() throws SQLException {}

    // -------------------------------------------------------------------------
    // XXX: Batch features
    // -------------------------------------------------------------------------

    @Override
    public void addBatch() throws SQLException {
        batches++;
        super.addBatch();
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        super.addBatch(sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        super.clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return super.executeBatch();
    }



    @Override
    public long[] executeLargeBatch() throws SQLException {
        return super.executeLargeBatch();
    }



    // -------------------------------------------------------------------------
    // XXX: Unsupported static statement execution features
    // -------------------------------------------------------------------------

    @Override
    public boolean execute(String sql) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }



    @Override
    public long executeLargeUpdate(String sql) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }

    @Override
    public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }

    @Override
    public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }

    @Override
    public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }



    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        throw new UnsupportedOperationException("No static statement methods can be called");
    }

    // -------------------------------------------------------------------------
    // XXX: Unsupported result set query features
    // -------------------------------------------------------------------------

    @Override
    public ResultSet executeQuery() throws SQLException {
        if (batches > 0)
            throw new UnsupportedOperationException("Cannot batch result queries");
        else
            return super.executeQuery();
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        throw new UnsupportedOperationException("Cannot batch result queries");
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        throw new UnsupportedOperationException("Cannot batch result queries");
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        throw new UnsupportedOperationException("Cannot batch result queries");
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        throw new UnsupportedOperationException("Cannot batch result queries");
    }
}