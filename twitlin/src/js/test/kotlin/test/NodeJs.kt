/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

@JsName("__dirname")
internal external val dirname: dynamic

@JsModule("fs")
@JsNonModule
@JsName("fs")
internal external val nodeFS: dynamic

@JsModule("path")
@JsNonModule
@JsName("path")
internal external val nodePath: dynamic

internal val RESOURCE_ROOT =
    nodePath.join(dirname, "../../../../../", "twitlin/build/processedResources/js/test")
