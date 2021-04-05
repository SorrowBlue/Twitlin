![Twitlin](public_images/banner.svg)

![Build and Deploy](https://github.com/SorrowBlue/Twitlin/workflows/Build%20and%20Deploy/badge.svg)
![Publish library to GitHub Packages](https://github.com/SorrowBlue/Twitlin/workflows/Publish%20library%20to%20GitHub%20Packages/badge.svg)

[![v1](https://img.shields.io/endpoint?url=https%3A%2F%2Ftwbadges.glitch.me%2Fbadges%2Fstandard)](https://developer.twitter.com/en/docs/twitter-api/v1)
[![v2](https://img.shields.io/endpoint?url=https%3A%2F%2Ftwbadges.glitch.me%2Fbadges%2Fv2)](https://developer.twitter.com/en/docs/twitter-api/early-access)

このプロジェクトは、TwitterAPIを使用できるフルKotlin製のマルチプラットフォームライブラリです。

* JVM, Android, JSをサポートしています。
* IOS,Nativeはまだサポートしていません。

## 導入と参考資料

```kotlin
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.initializeTwitlin

fun main() {
    initializeTwitlin(
        API_KEY,
        API_KEY_SECRET,
        AccessToken(OAUTH_TOKEN, OAUTH_TOKEN_SECRET, "", "")
    )
    runBlocking {
        TwitterAPI.statuses.homeTimeline()
            .onSuccess { tweets ->
                // タイムラインのツイートを出力します。
                tweets.forEach { println(it) }
            }
            .onError { errors ->
                // ネットワーク・クライアント・リクエストのエラーを出力します。
                errors.forEach { println(it) }
            }
    }
}
```

> [ここ](https://twitlin.sorrowblue.com/docs) で完全なドキュメントを入手できます。

**更に詳しい使い方についてはTwitlinガイドを読んでください**

## セットアップ

### 依存関係の追加

![twitlin](https://img.shields.io/github/v/release/SorrowBlue/Twitlin?include_prereleases)

このライブラリは、GithubPackagesからダウンロードできます。

Kotlin DSL:

```kotlin
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/SorrowBlue/twitlin")
        credentials {
            username = System.getenv("GITHUB_YOUR_USERNAME")
            password = System.getenv("GITHUB_YOUR_PACKAGES_TOKEN")
        }
    }
    // Twltlinが使用しているkotlinx.datetimeライブラリのために
    // kotlinx bintrayリポジトリを追加する必要があります。
    // まもなくjcenterになります。
    maven(url = "https://kotlin.bintray.com/kotlinx/")
}

dependencies {
    // KotlinMultiPlatform用
    implementation("com.sorrowblue.twitlin:twitlin:$TWITLIN_VERSION")
    // Android用
    implementation("com.sorrowblue.twitlin:twitlin-android:$TWITLIN_VERSION")
    // JS用
    implementation("com.sorrowblue.twitlin:twitlin-js:$TWITLIN_VERSION")
    // JVM用
    implementation("com.sorrowblue.twitlin:twitlin-jvm:$TWITLIN_VERSION")
}
```

**注意**

API 26未満で実行されているAndroidデバイスをターゲットにする場合は、
[ここ](https://github.com/Kotlin/kotlinx-datetime#using-in-your-projects) を見てください。
