/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package net.jescort.search;

import net.gelif.kernel.core.exception.SearchException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.Term;

import java.io.IOException;
import java.io.StringReader;

/**
 * Class containing helper methods.
 *
 * @author: admin@gelif.net
 */
public class IndexUtil
{

    /**
     * Create a lucene term from the first token of the input string.
     *
     * @param field The lucene document field to create a term with
     * @param input The input you wish to convert into a term
     * @return Lucene search term
     */
    public static final Term getTerm(String field, String input)
    {
        if (input == null || field == null)
        {
            return null;
        }
        Analyzer analyzer = SearchHelper.getAnalyzer();
        TokenStream tokenStream;
        StringBuilder stringBuilder = new StringBuilder();
        Term term = null;
        try
        {
            tokenStream = analyzer.reusableTokenStream(field, new StringReader(input));
            tokenStream.reset();
            CharTermAttribute termAtt = tokenStream.addAttribute(CharTermAttribute.class);
            while(tokenStream.incrementToken())
            {
                stringBuilder.append(termAtt.buffer(), 0, termAtt.length());
                term = new Term(field, stringBuilder.toString());
            }
        } catch (IOException e)
        {
            throw new SearchException(e);
        }
        return term;
    }
}
