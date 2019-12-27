import java.io.File
import org.slf4s.{Logger, LoggerFactory}
import scala.util.Try

class GitRevision

object GitRevision {
  val LOGGER: Logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    // Check the current Git revision
    val gitRevision = Try {
      LOGGER.info("Checking the git revision of the current project")
      val s = sys.process.Process("git rev-parse HEAD") lineStream_! sys.process.ProcessLogger(_ => {}, _ => {})
      s.head
    }.getOrElse("unknown").trim
    LOGGER.info("gitRevision: " + gitRevision)

    // Check the current Git revision
    val base: File = new File("./.git") // Using the working directory as base for readability
    val gitRevision1: String = Try {
      if (base.exists()) {
        sys.process.Process("git rev-parse HEAD").!!
      }
      else {
        "unknown"
      }
    }.getOrElse("unknown").trim
    LOGGER.info("gitRevision1: " + gitRevision1)
    LOGGER.info("不使用Try语句获取gitRevision可能会异常（不是git仓库）")
    val gitRevision2: String = Try {
      sys.process.Process("git rev-parse HEAD").!!
    }.getOrElse("unknown").trim
    LOGGER.info("gitRevision2: " + gitRevision2)

    //  def it(): Unit = {
    //    val proc = Process("ls")
    //    val (out, err) = (new StringBuffer(), new StringBuffer())
    //    val logger = ProcessLogger(
    //      out.append,
    //      err.append
    //    )
    //    proc.!!(logger)
    //    println(out.toString)
    //    println(err.toString)
    //  }
  }
}
