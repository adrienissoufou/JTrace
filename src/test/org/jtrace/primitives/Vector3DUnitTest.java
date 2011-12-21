package org.jtrace.primitives;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Vector3DUnitTest {

	private static final double MODULE_NON_ORIGIN_VECTOR = Math.sqrt(12);

	private static final double MODULE_ORIGIN_VECTOR = 0;

	private static final double PERPENDICULAR_VECTORS_DOT_PRODUCT_RESULT = 0;

	private static final double COLLINEAR_NON_NORMALIZED_VECTORS_DOT_PRODUCT_RESULT = 6;

	@Test
	public void testDotProduct_PerpendicularVectors() {
		final Vector3D v1 = new Vector3D(1, 0, 0);
		final Vector3D v2 = new Vector3D(0, 1, 0);

		Assert.assertEquals(v1.dot(v2),
				PERPENDICULAR_VECTORS_DOT_PRODUCT_RESULT);
	}

	@Test
	public void testDotProduct_CollinearVectors() {
		final Vector3D v1 = new Vector3D(1, 1, 1);
		final Vector3D v2 = new Vector3D(2, 2, 2);

		Assert.assertEquals(v1.dot(v2),
				COLLINEAR_NON_NORMALIZED_VECTORS_DOT_PRODUCT_RESULT);
	}

	@Test
	public void testModule_Vector_NonOrigin() {
		final Vector3D v = new Vector3D(2, 2, 2);

		Assert.assertEquals(v.module(), MODULE_NON_ORIGIN_VECTOR);
	}

	@Test
	public void testModule_Vector_Origin() {
		final Vector3D v = new Vector3D(0, 0, 0);

		Assert.assertEquals(v.module(), MODULE_ORIGIN_VECTOR);
	}

	@Test
	public void testNormalize_UnitVector() {
		final Vector3D v = new Vector3D(1, 0, 0);
		final Vector3D normalizedV = v.normal();

		final Vector3D expected = new Vector3D(1, 0, 0);

		Assert.assertEquals(normalizedV, expected);
	}

	@Test
	public void testNormalize_NotUnitVector() {
		final Vector3D v = new Vector3D(2, 3, 4);
		final Vector3D normalizedV = v.normal();

		final double module = Math.sqrt(29);
		final Vector3D expected = new Vector3D(2 / module, 3 / module,
				4 / module);

		Assert.assertEquals(normalizedV, expected);
	}

	@Test
	public void testTwoPointsConstructor_trivialCase() {
		Point3D a = new Point3D(0, 0, 0);
		Point3D b = new Point3D(2, 2, 2);

		Assert.assertEquals(new Vector3D(a, b), new Vector3D(2, 2, 2));
	}

	@Test
	public void testMultiply_By2() {
		Vector3D v = new Vector3D(1, 1, 1);
		Vector3D expected = new Vector3D(2, 2, 2);
		
		Assert.assertEquals(v.multiply(2), expected);
	}
	
	@Test
	public void testCross_byNilVector() {
		Vector3D base = new Vector3D(1, 1, 1);
		Vector3D nil = new Vector3D(0, 0, 0);
		
		Assert.assertEquals(base.cross(nil), nil);
	}
	
	@Test
	public void testCross_sameVector() {
		Vector3D vector = new Vector3D(2, 2, 2);
		Vector3D expected = new Vector3D(0, 0, 0);
		
		Assert.assertEquals(vector.cross(vector), expected);
	}
	
	@Test
	public void testCross_differentVectors() {
		Vector3D vector = new Vector3D(1, 2, 3);
		Vector3D otherVector = new Vector3D(4, 5, 6);
		Vector3D expected = new Vector3D(-3, 6, -3);
		
		Assert.assertEquals(vector.cross(otherVector), expected);
	}
}
