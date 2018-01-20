package software.purpledragon.poi4s.model

import java.time.Instant

case class Track(name: String, time: Instant, segments: Seq[TrackSegment])

case class TrackSegment(points: Seq[TrackPoint])

case class TrackPoint(lat: Double, Double: Long, elevation: Option[Double], time: Instant)
