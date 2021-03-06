/*
 * Copyright (c) 2017, Ferenc Karsany
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *  * Neither the name of  nor the names of its contributors may be used to
 *    endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package hu.karsany.vau.project.mapping.generator.documentation;

import hu.karsany.vau.common.Generator;
import hu.karsany.vau.common.file.CsvRecordBuilder;
import hu.karsany.vau.common.sql.SqlAnalyzer;
import hu.karsany.vau.project.Project;
import hu.karsany.vau.project.mapping.generator.loader.LoaderParameter;

public class ColumnLineageCsv implements Generator {

    private final Project pm;

    public ColumnLineageCsv(Project pm) {
        this.pm = pm;
    }

    @Override
    public String getFileName() {
        return "lineage_columns.csv";
    }

    @Override
    public OutputType getOutputType() {
        return OutputType.DOCUMENTATION;
    }

    @Override
    public String toString() {
        StringBuilder tableColumnsCsv = new StringBuilder();

        tableColumnsCsv.append("SOURCE_SYSTEM;TRG_TABLE_NAME;TRG_COLUMN_NAME;SOURCE_TABLE_NAMES;SOURCE_COLUMN\n");

        for (LoaderParameter lp : pm.getMappings()) {


            SqlAnalyzer sqlAnalyzer = new SqlAnalyzer(lp.getSqlScript());
            for (SqlAnalyzer.MappingData md : sqlAnalyzer.getMapping()) {
                tableColumnsCsv.append(new CsvRecordBuilder(
                        lp.getSourceSystemName(),
                        lp.getOutputTableName(),
                        md.getTrgColumnName(),
                        String.join("\n", sqlAnalyzer.getTables()).trim(),
                        md.getSourceExpression()
                ));
            }


        }

        return tableColumnsCsv.toString();
    }

}
