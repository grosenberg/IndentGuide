package net.certiv.tools.indentguide;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.themes.ColorUtil;
import org.osgi.framework.BundleContext;

import net.certiv.tools.indentguide.preferences.PreferenceConstants;

public class Activator extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "net.certiv.tools.indentguide"; //$NON-NLS-1$
	private static Activator plugin;
	private Color color;

	public Activator() {}

	/** Returns the shared instance */
	public static Activator getDefault() {
		return plugin;
	}

	public static void log(Throwable e) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage()));
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (color != null) {
			color.dispose();
			color = null;
		}
		plugin = null;
		super.stop(context);
	}

	public Color getColor() {
		if (color == null) {
			String colorString = getPreferenceStore().getString(PreferenceConstants.LINE_COLOR);
			color = new Color(PlatformUI.getWorkbench().getDisplay(), ColorUtil.getColorValue(colorString));
		}
		return color;
	}

	public void setColor(Color color) {
		if (this.color != null) {
			this.color.dispose();
		}
		this.color = color;
	}
}