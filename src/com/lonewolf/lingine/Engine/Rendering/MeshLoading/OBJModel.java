package com.lonewolf.lingine.Engine.Rendering.MeshLoading;

import com.lonewolf.lingine.Engine.CoreEngine.Util;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class OBJModel
{
	private ArrayList<Vector3f> m_positions;
	private ArrayList<Vector2f> m_texCoords;
	private ArrayList<Vector3f> m_normals;
	private ArrayList<OBJIndex> m_indices;
	private boolean             m_hasTexCoords;
	private boolean             m_hasNormals;

	public OBJModel(String fileName)
	{
		m_positions = new ArrayList<>();
		m_texCoords = new ArrayList<>();
		m_normals = new ArrayList<>();
		m_indices = new ArrayList<>();
		m_hasTexCoords = false;
		m_hasNormals = false;

		BufferedReader meshReader;

		try
		{
			meshReader = new BufferedReader(new FileReader(fileName));
			String line;

			while((line = meshReader.readLine()) != null)
			{
				String[] tokens = line.split(" ");
				tokens = Util.RemoveEmptyStrings(tokens);
				
				if (tokens.length != 0 && !tokens[0].equals("#"))
				{
					switch (tokens[0])
					{
						case "v":
							m_positions.add(new Vector3f(Float.parseFloat(tokens[1]),
									Float.parseFloat(tokens[2]),
									Float.parseFloat(tokens[3])));
							break;
						case "vt":
							m_texCoords.add(new Vector2f(Float.parseFloat(tokens[1]),
									1.0f - Float.parseFloat(tokens[2])));
							break;
						case "vn":
							m_normals.add(new Vector3f(Float.parseFloat(tokens[1]),
									Float.parseFloat(tokens[2]),
									Float.parseFloat(tokens[3])));
							break;
						case "f":
							for (int i = 0; i < tokens.length - 3; i++)
							{
								m_indices.add(ParseOBJIndex(tokens[1]));
								m_indices.add(ParseOBJIndex(tokens[2 + i]));
								m_indices.add(ParseOBJIndex(tokens[3 + i]));
							}
							break;
					}
				}
			}

			meshReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	public IndexedModel ToIndexedModel()
	{
		IndexedModel result = new IndexedModel();
		IndexedModel normalModel = new IndexedModel();
		HashMap<OBJIndex, Integer> resultIndexMap = new HashMap<>();
		HashMap<Integer, Integer> normalIndexMap = new HashMap<>();
		HashMap<Integer, Integer> indexMap = new HashMap<>();
		
		for (OBJIndex currentIndex : m_indices)
		{
			Vector3f currentPosition = m_positions.get(currentIndex.GetVertexIndex());
			Vector2f currentTexCoord;
			Vector3f currentNormal;
			
			if (m_hasTexCoords)
				currentTexCoord = m_texCoords.get(currentIndex.GetTexCoordIndex());
			else
				currentTexCoord = new Vector2f(0, 0);
			
			if (m_hasNormals)
				currentNormal = m_normals.get(currentIndex.GetNormalIndex());
			else
				currentNormal = new Vector3f(0, 0, 0);
			
			Integer modelVertexIndex = resultIndexMap.get(currentIndex);
			
			if (modelVertexIndex == null)
			{
				modelVertexIndex = result.getPositions().size();
				resultIndexMap.put(currentIndex, modelVertexIndex);
				
				result.getPositions().add(currentPosition);
				result.getTexCoords().add(currentTexCoord);
				if (m_hasNormals)
					result.getNormals().add(currentNormal);
			}
			
			Integer normalModelIndex = normalIndexMap.get(currentIndex.GetVertexIndex());
			
			if (normalModelIndex == null)
			{
				normalModelIndex = normalModel.getPositions().size();
				normalIndexMap.put(currentIndex.GetVertexIndex(), normalModelIndex);
				
				normalModel.getPositions().add(currentPosition);
				normalModel.getTexCoords().add(currentTexCoord);
				normalModel.getNormals().add(currentNormal);
				normalModel.getTangents().add(new Vector3f(0, 0, 0));
			}
			
			result.getIndices().add(modelVertexIndex);
			normalModel.getIndices().add(normalModelIndex);
			indexMap.put(modelVertexIndex, normalModelIndex);
		}

		if(!m_hasNormals)
		{
			normalModel.CalcNormals();

			for(int i = 0; i < result.getPositions().size(); i++)
				result.getNormals().add(normalModel.getNormals().get(indexMap.get(i)));
		}

		normalModel.CalcTangents();

		for(int i = 0; i < result.getPositions().size(); i++)
			result.getTangents().add(normalModel.getTangents().get(indexMap.get(i)));

		return result;
	}

	private OBJIndex ParseOBJIndex(String token)
	{
		String[] values = token.split("/");

		OBJIndex result = new OBJIndex();
		result.SetVertexIndex(Integer.parseInt(values[0]) - 1);

		if(values.length > 1)
		{
			if(!values[1].isEmpty())
			{
				m_hasTexCoords = true;
				result.SetTexCoordIndex(Integer.parseInt(values[1]) - 1);
			}

			if(values.length > 2)
			{
				m_hasNormals = true;
				result.SetNormalIndex(Integer.parseInt(values[2]) - 1);
			}
		}

		return result;
	}
}
