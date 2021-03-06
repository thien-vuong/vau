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

package hu.karsany.vau.project.datamodel.generator.other;

import hu.karsany.vau.common.Generator;
import hu.karsany.vau.common.templating.TemplateEvaluation;
import hu.karsany.vau.project.datamodel.model.Table;

public class DataModelExampleMapping implements Generator {


    private final Table table;

    public DataModelExampleMapping(Table table) {
        this.table = table;
    }

    @Override
    public String getFileName() {
        String qualifier = table.getTableName().substring(0, 1);
        String mainName = table.getTableName().substring(table.getTableName().indexOf('_') + 1);
        return (qualifier + "_##_" + mainName + "_m.sql").toLowerCase();
    }

    @Override
    public OutputType getOutputType() {
        return OutputType.EXAMPLE;
    }

    @Override
    public String toString() {
        return new TemplateEvaluation("mapping_sample.sql", table).toString();
    }
}
