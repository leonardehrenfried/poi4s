package software.purpledragon.poi4s.gpx

import java.time.OffsetDateTime

import org.scalatest.{FlatSpec, Matchers}
import software.purpledragon.poi4s.model.TrackPoint
import software.purpledragon.xml.scalatest.XmlMatchers

class TrackParserSpec extends FlatSpec with Matchers with XmlMatchers {
  val parser = new GpxParser()

  "Track feature" should "parse a valid Runkeeper GPX 1.1 file" in {
    val file = parser.parseFile(getClass.getResourceAsStream("/gpx/1.1/tracks.gpx"))
    file.version shouldBe Some(GpxVersion.Version11)

    file.track should be('defined)

    val track = file.track.get
    track.name should be("Running 1/20/18 2:45 pm")
    track.time should be(OffsetDateTime.parse("2018-01-20T13:45:13Z").toInstant)

    track.segments should have size 4
    track.segments.head.points should have size 239
    track.segments.head.points.head should be(
      TrackPoint(52.502843, 13.415284, Some(42.8), OffsetDateTime.parse("2018-01-20T13:45:13Z").toInstant))
  }

}
