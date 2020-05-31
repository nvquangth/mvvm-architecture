buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(kotlin(module = "gradle-plugin", version = com.example.buildsrc.Versions.kotlin))
        classpath(com.example.buildsrc.BuildPlugins.androidPlugin)
        classpath(com.example.buildsrc.BuildPlugins.koinPlugin)
        classpath(com.example.buildsrc.BuildPlugins.navSafeArg)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

apply(plugin = "koin")