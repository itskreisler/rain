package cli

import "fmt"

// manifestXML genera el AndroidManifest.xml del proyecto.
func manifestXML(name, pkg, template string) string {
	devlog("manifestXML", fmt.Sprintf("name=%s, pkg=%s, template=%s", name, pkg, template), "Genera el contenido de AndroidManifest.xml")
	theme := "@style/Theme.Rain"
	return fmt.Sprintf(`<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="%s">

    <application
        android:label="@string/app_name"
        android:icon="@android:drawable/sym_def_app_icon"
        android:theme="%s"
        android:allowBackup="true">
        <activity android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
`, pkg, theme)
}

// stringsXML genera los recursos de cadena.
func stringsXML(name string) string {
	devlog("stringsXML", fmt.Sprintf("name=%s", name), "Genera el archivo strings.xml")
	return fmt.Sprintf(`<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">%s</string>
    <string name="greeting">Hola desde Rain 🌧️</string>
    <string name="tap_me">Tocar</string>
</resources>
`, name)
}

// themesXML genera un tema base; Compose necesita un tema sin ActionBar.
func themesXML(template string) string {
	devlog("themesXML", fmt.Sprintf("template=%s", template), "Genera el archivo themes.xml")
	parent := "@android:style/Theme.DeviceDefault"
	if template == "completo" {
		parent = "@android:style/Theme.Material.Light.NoActionBar"
	}
	return fmt.Sprintf(`<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="Theme.Rain" parent="%s" />
</resources>
`, parent)
}

// javaMainActivity es la actividad de la plantilla simple (vistas programáticas).
func javaMainActivity(pkg string) string {
	devlog("javaMainActivity", fmt.Sprintf("pkg=%s", pkg), "Genera MainActivity.java para la plantilla simple")
	return fmt.Sprintf(`package %s;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private int count = 0;
    private TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(48, 48, 48, 48);

        TextView title = new TextView(this);
        title.setText(getString(R.string.greeting));
        title.setTextSize(24);
        title.setGravity(Gravity.CENTER);

        counter = new TextView(this);
        counter.setTextSize(18);
        counter.setGravity(Gravity.CENTER);
        updateCounter();

        Button button = new Button(this);
        button.setText(getString(R.string.tap_me));
        button.setOnClickListener(v -> {
            count++;
            updateCounter();
        });

        root.addView(title);
        root.addView(counter);
        root.addView(button);
        setContentView(root);
    }

    private void updateCounter() {
        counter.setText("Tocaste " + count + (count == 1 ? " vez" : " veces"));
    }
}
`, pkg)
}

// kotlinComposeMainActivity es la plantilla completo: Kotlin + Compose real.
func kotlinComposeMainActivity(pkg string) string {
	devlog("kotlinComposeMainActivity", fmt.Sprintf("pkg=%s", pkg), "Genera MainActivity.kt con Jetpack Compose para la plantilla completo")
	return fmt.Sprintf(`package %s

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vinceglb.confettikit.compose.ConfettiKit
import io.github.vinceglb.confettikit.compose.rememberConfettiKitState
import io.github.vinceglb.confettikit.core.Party
import io.github.vinceglb.confettikit.core.emitter.Emitter
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Confeti()
                }
            }
        }
    }
}

@Composable
fun Confeti() {
    var animando by remember { mutableStateOf(false) }
    val confetiState = rememberConfettiKitState()
    val parties = remember {
        listOf(
            Party(
                speed = 0f,
                maxSpeed = 25f,
                damping = 0.9f,
                spread = 360,
                colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                emitter = Emitter(duration = 120.milliseconds).max(80),
            ),
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("Hola desde Rain + Compose 🌧️")
            Button(onClick = {
                confetiState.reset()
                animando = true
            }) {
                Text("Lanzar confeti")
            }
        }

        if (animando) {
            ConfettiKit(
                modifier = Modifier.fillMaxSize(),
                parties = parties,
                state = confetiState,
                onParticleSystemEnded = { _, activeSystems ->
                    if (activeSystems == 0) {
                        animando = false
                    }
                },
            )
        }
    }
}
`, pkg)
}
