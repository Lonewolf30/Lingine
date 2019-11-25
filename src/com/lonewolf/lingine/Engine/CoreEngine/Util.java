package com.lonewolf.lingine.Engine.CoreEngine;

import com.lonewolf.lingine.Engine.Rendering.Vertex;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class Util
{
	public static FloatBuffer CreateFloatBuffer(int size)
	{
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static IntBuffer CreateIntBuffer(int size)
	{
		return BufferUtils.createIntBuffer(size);
	}

	public static ByteBuffer CreateByteBuffer(int size)
	{
		return BufferUtils.createByteBuffer(size);
	}
	
	public static IntBuffer CreateFlippedBuffer(int... values)
	{
		IntBuffer buffer = CreateIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer CreateFlippedBuffer(Vertex[] vertices)
	{
		FloatBuffer buffer = CreateFloatBuffer(vertices.length * Vertex.SIZE);
		
		for (Vertex vertex : vertices)
		{
			buffer.put(vertex.GetPos().GetX());
			buffer.put(vertex.GetPos().GetY());
			buffer.put(vertex.GetPos().GetZ());
			buffer.put(vertex.GetTexCoord().GetX());
			buffer.put(vertex.GetTexCoord().GetY());
			buffer.put(vertex.GetNormal().GetX());
			buffer.put(vertex.GetNormal().GetY());
			buffer.put(vertex.GetNormal().GetZ());
			buffer.put(vertex.GetTangent().GetX());
			buffer.put(vertex.GetTangent().GetY());
			buffer.put(vertex.GetTangent().GetZ());
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer CreateFlippedBuffer(Matrix4f value)
	{
		FloatBuffer buffer = CreateFloatBuffer(4 * 4);
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				buffer.put(value.Get(i, j));
		
		buffer.flip();
		
		return buffer;
	}
	
	public static String[] RemoveEmptyStrings(String[] data)
	{
		ArrayList<String> result = new ArrayList<>();
		
		for (String datum : data)
			if (!datum.equals(""))
				result.add(datum);
		
		String[] res = new String[result.size()];
		result.toArray(res);
		
		return res;
	}
	
	public static int[] ToIntArray(Integer[] data)
	{
		int[] result = new int[data.length];
		
		for(int i = 0; i < data.length; i++)
			result[i] = data[i];
		
		return result;
	}
}
