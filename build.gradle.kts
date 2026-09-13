// Root build file. Individual module configuration lives in each module's build.gradle.kts.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ktlint.gradle)
}

// Applied to every module (including this root project) so `./gradlew ktlintCheck` at the repo
// root aggregates each module's check. Rule configuration lives entirely in the root
// .editorconfig, which ktlint reads automatically — no plugin-specific DSL config needed.
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
