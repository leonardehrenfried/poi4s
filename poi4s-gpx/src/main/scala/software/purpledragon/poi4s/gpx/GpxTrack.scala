/*
 * Copyright 2017 Michael Stringer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package software.purpledragon.poi4s.gpx

import software.purpledragon.poi4s.model.{Track, TrackPoint, TrackSegment}
import software.purpledragon.poi4s.util.XmlUtils._

import scala.xml.Node

private[gpx] object GpxTrack {

  def parseVersion11(node: Node): Track = {
    Track(
      (node \\ "name").text,
      (node \\ "time").instantOption.get,
      (node \\ "trkseg").map(parseSegment)
    )
  }

  private def parseSegment(node: Node): TrackSegment =
    TrackSegment(
      (node \\ "trkpt").map(parsePoint)
    )

  private def parsePoint(node: Node): TrackPoint =
    TrackPoint(
      (node \@ "lat").toDouble,
      (node \@ "lon").toDouble,
      (node \ "ele").doubleOption,
      (node \ "time").instantOption.get
    )
}
