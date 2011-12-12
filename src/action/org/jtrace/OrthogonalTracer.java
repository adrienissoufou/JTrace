package org.jtrace;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jtrace.geometry.Sphere;
import org.jtrace.primitives.ColorRGB;
import org.jtrace.primitives.Point3D;
import org.jtrace.primitives.Vector3D;

public class OrthogonalTracer {
	private static ColorRGB BACKGROUND_COLOR = new ColorRGB(0, 0, 0);
	private static ColorRGB DEFAULT_COLOR = new ColorRGB(255, 0, 0);

	private List<Sphere> world;
	private ViewPlane viewPlane;

	public ColorRGB trace(Jay jay) {
		for (Sphere sphere : world) {
			if (sphere.hit(jay)) {
				return DEFAULT_COLOR;
			}
		}

		return BACKGROUND_COLOR;
	}
	
	public void render() throws IOException
	{
		double x, y;
		int hres = viewPlane.getHres();
		int vres = viewPlane.getVres();
		double s = viewPlane.getPixelSize();
		
		Vector3D direction = new Vector3D(0, 0, -1);
		
		BufferedImage bi = new BufferedImage(hres, vres, BufferedImage.TYPE_INT_RGB);
		
		for (int r = 0; r < vres; r++) {
			for (int c = 0; c < hres; c++) {
				x = s*(c - 0.5 * (hres - 1.0));
				y = s*(r - 0.5 * (vres - 1.0));
				
				Point3D origin = new Point3D(x, y, 100);
				
				Jay jay = new Jay(origin, direction);
				
				ColorRGB color = trace(jay);
				
				bi.setRGB(r, c, color.getR() + color.getG() + color.getB());
			}
		}
		
		ImageIO.write(bi, "jpeg", new File("result.jpg"));
	}

	public static void main(String[] args) throws IOException {
		ViewPlane vp = new ViewPlane(200, 200, 1.25);
		Point3D c = new Point3D(0, 0, 0);
		Sphere s = new Sphere(c, 85.0f);
		
		List<Sphere> world = new ArrayList<Sphere>();
		
		world.add(s);
		
		OrthogonalTracer ot = new OrthogonalTracer();
		ot.setViewPlane(vp);
		ot.setWorld(world);
		
		ot.render();
	}
	
	public List<Sphere> getWorld() {
		return world;
	}

	public void setWorld(List<Sphere> world) {
		this.world = world;
	}

	public ViewPlane getViewPlane() {
		return viewPlane;
	}

	public void setViewPlane(ViewPlane viewPlane) {
		this.viewPlane = viewPlane;
	}

}