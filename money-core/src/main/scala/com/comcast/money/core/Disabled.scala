/*
 * Copyright 2012 Comcast Cable Communications Management, LLC
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

package com.comcast.money.core

import com.comcast.money.api._
import io.opentelemetry.api.common.{ AttributeKey, Attributes }
import io.opentelemetry.context.{ Context, Scope }
import io.opentelemetry.api.trace.{ SpanContext, StatusCode, Span => OtelSpan }

import com.comcast.money.core.formatters.Formatter

// $COVERAGE-OFF$
object DisabledSpanHandler extends SpanHandler {

  override def handle(spanInfo: SpanInfo): Unit = ()
}

object DisabledTracer extends Tracer {

  val spanFactory: SpanFactory = DisabledSpanFactory

  override def startSpan(key: String): Span = DisabledSpan

  override def time(key: String): Unit = ()

  override def record(key: String, measure: Double): Unit = ()

  override def record(key: String, measure: Double, propagate: Boolean): Unit = ()

  override def record(key: String, measure: String): Unit = ()

  override def record(key: String, measure: String, propagate: Boolean): Unit = ()

  override def record(key: String, measure: Long): Unit = ()

  override def record(key: String, measure: Long, propagate: Boolean): Unit = ()

  override def record(key: String, measure: Boolean): Unit = ()

  override def record(key: String, measure: Boolean, propagate: Boolean): Unit = ()

  override def record(note: Note[_]): Unit = ()

  override def stopSpan(result: Boolean): Unit = ()

  override def startTimer(key: String): Scope = () => ()

  override def stopTimer(key: String): Unit = ()

  override def close(): Unit = ()
}

object DisabledFormatter extends Formatter {
  override def toHttpHeaders(spanId: SpanId, addHeader: (String, String) => Unit): Unit = ()

  override def fromHttpHeaders(headers: Iterable[String], getHeader: String => String, log: String => Unit): Option[SpanId] = None

  override def fields: Seq[String] = Nil

  override def setResponseHeaders(getHeader: String => String, addHeader: (String, String) => Unit): Unit = ()
}

object DisabledSpanFactory extends SpanFactory {

  override def spanBuilder(spanName: String): Span.Builder = DisabledSpanBuilder

  override def newSpan(spanName: String): Span = DisabledSpan

  override def childSpan(childName: String, span: Span): Span = DisabledSpan

  override def childSpan(childName: String, span: Span, sticky: Boolean): Span = DisabledSpan

  override def newSpan(spanId: SpanId, spanName: String): Span = DisabledSpan
}

object DisabledSpanBuilder extends Span.Builder {
  override def setParent(context: Context): Span.Builder = this

  override def setParent(span: Span): Span.Builder = this

  override def setParent(span: Option[Span]): Span.Builder = this

  override def setSticky(sticky: Boolean): Span.Builder = this

  override def setNoParent(): Span.Builder = this

  override def addLink(spanContext: SpanContext): Span.Builder = this

  override def addLink(spanContext: SpanContext, attributes: Attributes): Span.Builder = this

  override def setAttribute(key: String, value: String): Span.Builder = this

  override def setAttribute(key: String, value: Long): Span.Builder = this

  override def setAttribute(key: String, value: Double): Span.Builder = this

  override def setAttribute(key: String, value: Boolean): Span.Builder = this

  override def setAttribute[T](key: AttributeKey[T], value: T): Span.Builder = this

  override def record(note: Note[_]): Span.Builder = this

  override def setSpanKind(spanKind: OtelSpan.Kind): Span.Builder = this

  override def setStartTimestamp(startTimestamp: Long): Span.Builder = this

  override def startSpan(): Span = DisabledSpan
}

object DisabledSpan extends Span {

  override def stop(): Unit = ()

  override def stop(result: java.lang.Boolean): Unit = ()

  override def stopTimer(timerKey: String): Unit = ()

  override def record(note: Note[_]): Span = this

  override def startTimer(timerKey: String): Scope = () => ()

  override def info(): SpanInfo = null

  override def attachScope(scope: Scope): Span = this

  override def close(): Unit = ()

  override def setAttribute(key: String, value: String): Span = this

  override def setAttribute(key: String, value: Long): Span = this

  override def setAttribute(key: String, value: Double): Span = this

  override def setAttribute(key: String, value: Boolean): Span = this

  override def setAttribute[T](key: AttributeKey[T], value: T): Span = this

  override def addEvent(name: String): Span = this

  override def addEvent(name: String, timestamp: Long): Span = this

  override def addEvent(name: String, attributes: Attributes): Span = this

  override def addEvent(name: String, attributes: Attributes, timestamp: Long): Span = this

  override def setStatus(canonicalCode: StatusCode): Span = this

  override def setStatus(canonicalCode: StatusCode, description: String): Span = this

  override def recordException(exception: Throwable): Span = this

  override def recordException(exception: Throwable, additionalAttributes: Attributes): Span = this

  override def updateName(name: String): Span = this

  override def `end`(): Unit = ()

  override def `end`(endTimeStamp: Long): Unit = ()

  override def getSpanContext: SpanContext = SpanContext.getInvalid

  override def isRecording: Boolean = false
}
// $COVERAGE-ON$
