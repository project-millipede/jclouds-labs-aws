/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.rds.options;

import java.util.Set;

import org.jclouds.http.options.BaseHttpRequestOptions;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * Options used to list available instances.
 * 
 * @see <a
 *      href="http://docs.amazonwebservices.com/AmazonRDS/latest/APIReference/API_DescribeInstances.html"
 *      >docs</a>
 */
public class ListInstancesOptions extends BaseHttpRequestOptions implements Cloneable {

   private Object marker;
   private Set<String> names = Sets.newLinkedHashSet();

   /**
    * Use this parameter only when paginating results, and only in a subsequent request after you've
    * received a response where the results are truncated. Set it to the value of the Marker element
    * in the response you just received.
    */
   public ListInstancesOptions afterMarker(Object marker) {
      this.marker = marker;
      return this;
   }

   /**
    * list of names associated with the Instances at creation time.
    */
   public ListInstancesOptions names(Set<String> names) {
      this.names = names;
      return this;
   }

   /**
    * @see #names
    */
   public ListInstancesOptions name(String name) {
      this.names.add(name);
      return this;
   }

   public static class Builder {

      /**
       * @see ListInstancesOptions#getMarker()
       */
      public static ListInstancesOptions afterMarker(Object marker) {
         return new ListInstancesOptions().afterMarker(marker);
      }

      /**
       * @see ListInstancesOptions#getNames()
       */
      public static ListInstancesOptions name(String name) {
         return new ListInstancesOptions().name(name);
      }

      /**
       * @see ListInstancesOptions#getNames()
       */
      public static ListInstancesOptions names(Set<String> names) {
         return new ListInstancesOptions().names(names);
      }
   }

   @Override
   public Multimap<String, String> buildFormParameters() {
      Multimap<String, String> params = super.buildFormParameters();
      if (marker != null)
         params.put("Marker", marker.toString());
      if (names.size() > 0) {
         int nameIndex = 1;
         for (String name : names) {
            params.put("InstanceNames.member." + nameIndex, name);
            nameIndex++;
         }
      }
      return params;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public int hashCode() {
      return Objects.hashCode(marker, names);
   }

   @Override
   public ListInstancesOptions clone() {
      return new ListInstancesOptions().afterMarker(marker).names(names);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      ListInstancesOptions other = ListInstancesOptions.class.cast(obj);
      return Objects.equal(this.marker, other.marker) && Objects.equal(this.names, other.names);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this).omitNullValues().add("marker", marker).add("names", names).toString();
   }
}
