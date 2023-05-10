package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component

public class JsonFileRecup {

	public void recupFile() throws StreamReadException, DatabindException, IOException {

		// Recupération des données dans le fichier json
		ObjectMapper objectMapper = new ObjectMapper();
		String path = "src/main/resources/data/data.json";
		Map<String, Object> map = objectMapper.readValue(new File(path), new TypeReference<Map<String, Object>>() {
		});
		System.out.println(map);

		for (String entry : map.keySet()) {
			Object objectMap = map.get(entry);
			ArrayList<Map> liste = new ArrayList<Map>();
			liste = (ArrayList<Map>) objectMap;
			for (int i = 0; i < liste.size(); i++) {
				Map<String, String> mapListe = liste.get(i);
				for (String entry2 : mapListe.keySet()) {
					String sval = mapListe.get(entry2);
					System.out.println("1-" + sval);
				}
			}

			System.out.println("1-" + liste);
		}
	}

}
