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
package org.jclouds.elb.domain.regionscoped;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

public class RegionAndName {
   public static RegionAndName fromSlashEncoded(String name) {
      Iterable<String> parts = Splitter.on('/').split(checkNotNull(name, "name"));
      checkArgument(Iterables.size(parts) == 2, "name must be in format regionId/name");
      return new RegionAndName(Iterables.get(parts, 0), Iterables.get(parts, 1));
   }

   public static RegionAndName fromRegionAndName(String regionId, String name) {
      return new RegionAndName(regionId, name);
   }

   private static String slashEncodeRegionAndName(String regionId, String name) {
      return checkNotNull(regionId, "regionId") + "/" + checkNotNull(name, "name");
   }

   public String slashEncode() {
      return slashEncodeRegionAndName(regionId, name);
   }

   protected final String regionId;
   protected final String name;

   protected RegionAndName(String regionId, String name) {
      this.regionId = checkNotNull(regionId, "regionId");
      this.name = checkNotNull(name, "name");
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(regionId, name);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      RegionAndName other = (RegionAndName) obj;
      return Objects.equal(regionId, other.regionId) && Objects.equal(name, other.name);
   }

   public String getRegion() {
      return regionId;
   }

   public String getName() {
      return name;
   }

   @Override
   public String toString() {
      return "[regionId=" + regionId + ", name=" + name + "]";
   }

}
