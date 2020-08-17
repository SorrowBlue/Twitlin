plugins {
	`kotlin-dsl`
}

repositories {
	jcenter()
	google()
	maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
}

dependencies {
	implementation("com.android.tools.build:gradle:4.0.1")
	implementation(kotlin("gradle-plugin", "1.4.0"))

}
