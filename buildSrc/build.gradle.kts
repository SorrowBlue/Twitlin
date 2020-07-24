plugins {
	`kotlin-dsl`
}

repositories {
	jcenter()
	google()
}

dependencies {
	implementation("com.android.tools.build:gradle:3.5.4")
	implementation(kotlin("gradle-plugin", "1.3.72"))
}
