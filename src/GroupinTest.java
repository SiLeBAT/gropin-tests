import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sbml.jsbml.AssignmentRule;
import org.sbml.jsbml.Model;

import com.sun.star.beans.PropertyValue;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XController;
import com.sun.star.frame.XModel;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.sheet.XSpreadsheetView;
import com.sun.star.sheet.XSpreadsheets;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

import de.bund.bfr.pmfml.file.PrimaryModelWDataFile;
import de.bund.bfr.pmfml.model.PrimaryModelWData;
import de.bund.bfr.pmfml.sbml.ModelRule;
import de.bund.bfr.pmfml.sbml.PMFCompartment;
import de.bund.bfr.pmfml.sbml.PMFSpecies;
import de.bund.bfr.pmfml.sbml.Reference;
import de.bund.bfr.pmfml.sbml.SBMLFactory;
import de.unirostock.sems.cbarchive.CombineArchiveException;

public class GroupinTest {

	enum GropinColumns {
		/** Model type (A). */
		model_type,

		/** Model's sequential numbering (B). */
		sequential_numbering,

		/** Number of variables of the model (C). */
		num_var,

		/** Substrate (D). */
		substrate,

		/** x variable (E). */
		x_var,

		/** x minimum value (F). */
		x_var_min,

		/** x maximum value (G). */
		x_var_max,

		/** y variable (H). */
		y_var,

		/** y minimum value (I). */
		y_var_min,

		/** y maximum value (J). */
		y_var_max,

		/** z variable (K). */
		z_var,

		/** z minimum value (L). */
		z_var_min,

		/** z maximum value (M). */
		z_var_max,

		/** d variable (N). */
		d_var,

		/** d minimum value (O). */
		d_var_min,

		/** d maximum value (P). */
		d_var_max,

		/** e variable (Q). */
		e_var,

		/** e minimum value (R). */
		e_var_min,

		/** e maximum value (S). */
		e_var_max,

		/** f variable (T). */
		f_var,

		/** f minimum value (U). */
		f_var_min,

		/** f maximum value (V). */
		f_var_max,

		/** g variable (W). */
		g_var,

		/** g minimum value (X). */
		g_var_min,

		/** g maximum value (Y). */
		g_var_max,

		/** h variable (Z). */
		h_var,

		/** h minimum value (AA). */
		h_var_min,

		/** h maximum value (AB). */
		h_var_max,

		/** i variable (AC). */
		i_var,

		/** i minimum value (AD). */
		i_var_min,

		/** i maximum value (AE). */
		i_var_max,

		/** paper's authors (AF). */
		authors,

		/** paper (AG). */
		paper,

		/** journal (AH). */
		journal,

		/** issue (AI). */
		issue,

		/** j variable (AJ). */
		j_var,

		/** j minimum value (AK). */
		j_var_min,

		/** j maximum value (AL). */
		j_var_max,

		/** inactive (AM). */
		inactive,

		/** microorganisms (AN). */
		microorganisms,

		/** first author and year of publication (AO). */
		first_author_and_year,

		/** product (AP). */
		product,

		/** psicheck (AQ). */
		psicheck,

		/** empty (AR). */
		empty_ar,

		/** dmri (AS). */
		dmri,

		/** model categ (AT). */
		model_categ,

		/** integrated (AU). */
		integrated,

		/** mumaxUn (AV). */
		mumax_un,

		/** aug/zu (AW). */
		aug_zu,

		/** rate label (AX). */
		rate_label,

		/** special notes (AY). */
		special_notes,

		/** reference equation (AZ). */
		reference_equation,

		/** co1 (BA). */
		co1,

		/** co1 val (BB). */
		co1_val,

		/** co2 (BC). */
		co2,

		/** co2 val (BD). */
		co2_val,

		/** co3 (BE). */
		co3,

		/** co3 val (BF). */
		co3_val,

		/** co4 (BG). */
		co4,

		/** co4 val (BH). */
		co4_val,

		/** co5 (BI). */
		co5,

		/** co5 val (BJ). */
		co5_val,

		/** co6 (BK). */
		co6,

		/** co6 val (BL). */
		co6_val,

		/** co7 (BM). */
		co7,

		/** co7 val (BN). */
		co7_val,

		/** co8 (BO). */
		co8,

		/** co8 val (BP). */
		co8_val,

		/** co9 (BQ). */
		co9,

		/** co9 val (BR). */
		co9_val,

		/** co10 (BS). */
		co10,

		/** co10 val (BT). */
		co10_val,

		/** co11 (BU). */
		co11,

		/** co11 val (BV). */
		co11_val,

		/** co12 (BW). */
		co12,

		/** co12 val (BX). */
		co12_val,

		/** co13 (BY). */
		co13,

		/** co13 val (BZ). */
		co13_val,

		/** co14 (CA). */
		co14,

		/** co14 val (CB). */
		co14_val,

		/** co15 (CC). */
		co15,

		/** co15 val (CD). */
		co15_val,

		/** co16 (CE). */
		co16,

		/** co16 (CF). */
		co16_val,

		/** co17 (CG). */
		co17,

		/** co17 val (CH). */
		co17_val,

		/** co18 (CI). */
		co18,

		/** co18 val (CJ). */
		co18_val,

		/** co19 (CK). */
		co19,

		/** co19 val (CL). */
		co19_val,

		/** co20 (CM). */
		co20,

		/** co20 val (CN). */
		co20_val,

		/** cases of possible simulations (CO). */
		possible_simulations,

		/** format of the rate solution (CP). */
		rate_solution_format,

		/** equation (CQ). */
		equation
	}

	private static Map<GropinColumns, String> headers;

	static {
		headers = new HashMap<>();
		headers.put(GropinColumns.model_type, "Model");
		headers.put(GropinColumns.sequential_numbering, "Microorganism");
		headers.put(GropinColumns.num_var, "Var");
		headers.put(GropinColumns.substrate, "Substrate");
		headers.put(GropinColumns.x_var, "x");
		headers.put(GropinColumns.x_var_min, "from");
		headers.put(GropinColumns.x_var_max, "to");
		headers.put(GropinColumns.y_var, "y");
		headers.put(GropinColumns.y_var_min, "from");
		headers.put(GropinColumns.y_var_max, "to");
		headers.put(GropinColumns.z_var, "z");
		headers.put(GropinColumns.z_var_min, "from");
		headers.put(GropinColumns.z_var_max, "to");
		headers.put(GropinColumns.d_var, "d");
		headers.put(GropinColumns.d_var_min, "from");
		headers.put(GropinColumns.d_var_max, "to");
		headers.put(GropinColumns.e_var, "e");
		headers.put(GropinColumns.e_var_min, "from");
		headers.put(GropinColumns.e_var_max, "to");
		headers.put(GropinColumns.f_var, "f");
		headers.put(GropinColumns.f_var_min, "from");
		headers.put(GropinColumns.f_var_max, "to");
		headers.put(GropinColumns.g_var, "g");
		headers.put(GropinColumns.g_var_min, "from");
		headers.put(GropinColumns.g_var_max, "to");
		headers.put(GropinColumns.h_var, "h");
		headers.put(GropinColumns.h_var_min, "from");
		headers.put(GropinColumns.h_var_max, "to");
		headers.put(GropinColumns.i_var, "i");
		headers.put(GropinColumns.i_var_min, "from");
		headers.put(GropinColumns.i_var_max, "to");
		headers.put(GropinColumns.authors, "Authors");
		headers.put(GropinColumns.paper, "Paper");
		headers.put(GropinColumns.journal, "");
		headers.put(GropinColumns.issue, "Issue");
		headers.put(GropinColumns.j_var, "j");
		headers.put(GropinColumns.j_var_min, "from");
		headers.put(GropinColumns.j_var_max, "to");
		headers.put(GropinColumns.inactive, "INACTIVE");
		headers.put(GropinColumns.microorganisms, "M/O");
		headers.put(GropinColumns.first_author_and_year, "First author");
		headers.put(GropinColumns.product, "Product");
		headers.put(GropinColumns.psicheck, "Psicheck");
		headers.put(GropinColumns.empty_ar, "");
		headers.put(GropinColumns.dmri, "DMRI");
		headers.put(GropinColumns.model_categ, "MODELCATEG");
		headers.put(GropinColumns.integrated, "INTEGRATED");
		headers.put(GropinColumns.mumax_un, "mumaxUn");
		headers.put(GropinColumns.aug_zu, "AUG/ZU");
		headers.put(GropinColumns.rate_label, "AX");
		headers.put(GropinColumns.special_notes, "Species notes");
		headers.put(GropinColumns.reference_equation, "Reference equation");
		headers.put(GropinColumns.co1, "Co1");
		headers.put(GropinColumns.co1_val, "Co1val");
		headers.put(GropinColumns.co2, "Co2");
		headers.put(GropinColumns.co2_val, "Co2val");
		headers.put(GropinColumns.co3, "Co3");
		headers.put(GropinColumns.co3_val, "Co3val");
		headers.put(GropinColumns.co4, "Co4");
		headers.put(GropinColumns.co4_val, "Co4val");
		headers.put(GropinColumns.co5, "Co5");
		headers.put(GropinColumns.co5_val, "Co5val");
		headers.put(GropinColumns.co6, "Co6");
		headers.put(GropinColumns.co6_val, "Co6val/FsT:Variables!A2");
		headers.put(GropinColumns.co7, "Co7");
		headers.put(GropinColumns.co7_val, "Co7val/Fsaw:Variables!B2");
		headers.put(GropinColumns.co8, "Co8");
		headers.put(GropinColumns.co8_val, "Co8val/FspH:Variables!C2");
		headers.put(GropinColumns.co9, "Co9");
		headers.put(GropinColumns.co9_val, "Co9val/FsPhe:Variables!E2");
		headers.put(GropinColumns.co10, "Co10");
		headers.put(GropinColumns.co10_val, "Co10val/Fsnit:Variables!H2");
		headers.put(GropinColumns.co11, "Co11");
		headers.put(GropinColumns.co11_val, "Co11val/FsCO2:Variables!F2");
		headers.put(GropinColumns.co12, "Co12");
		headers.put(GropinColumns.co12_val, "Co12val/Fsorg");
		headers.put(GropinColumns.co13, "Co13/Fdorg");
		headers.put(GropinColumns.co13_val, "Co13val");
		headers.put(GropinColumns.co14, "Co14");
		headers.put(GropinColumns.co14_val, "Co14val");
		headers.put(GropinColumns.co15, "Co15");
		headers.put(GropinColumns.co15_val, "Co15val");
		headers.put(GropinColumns.co16, "Co16");
		headers.put(GropinColumns.co16_val, "Co16val");
		headers.put(GropinColumns.co17, "Co17");
		headers.put(GropinColumns.co17_val, "Co17val");
		headers.put(GropinColumns.co18, "Co18");
		headers.put(GropinColumns.co18_val, "Co18val");
		headers.put(GropinColumns.co19, "Co19");
		headers.put(GropinColumns.co19_val, "Co19val");
		headers.put(GropinColumns.co20, "Co20");
		headers.put(GropinColumns.co20_val, "Co20val");
		headers.put(GropinColumns.possible_simulations, "Type of simulation");
		headers.put(GropinColumns.rate_solution_format, "mumax");
		headers.put(GropinColumns.equation, "equation");
	}

	public static void main(String[] args) {
		try {
			// get the remote office component context
			XComponentContext xRemoteContext = Bootstrap.bootstrap();
			if (xRemoteContext == null) {
				System.err
						.println("ERROR: Could not bootstrap default Office.");
			}

			XMultiComponentFactory xRemoteServiceManager = xRemoteContext
					.getServiceManager();

			Object desktop = xRemoteServiceManager.createInstanceWithContext(
					"com.sun.star.frame.Desktop", xRemoteContext);
			XComponentLoader xComponentLoader = UnoRuntime.queryInterface(
					XComponentLoader.class, desktop);

			PropertyValue[] loadProps = new PropertyValue[0];
			XComponent xSpreadsheetComponent = xComponentLoader
					.loadComponentFromURL("private:factory/scalc", "_blank", 0,
							loadProps);

			XSpreadsheetDocument xSpreadsheetDocument = UnoRuntime
					.queryInterface(XSpreadsheetDocument.class,
							xSpreadsheetComponent);

			XSpreadsheets xSpreadsheets = xSpreadsheetDocument.getSheets();
			xSpreadsheets.insertNewByName("MySheet", (short) 0);

			Object sheet = xSpreadsheets.getByName("MySheet");
			XSpreadsheet xSpreadsheet = UnoRuntime.queryInterface(
					XSpreadsheet.class, sheet);

			setHeaders(xSpreadsheet);
			setModels(xSpreadsheet);

			XModel xSpreadsheetModel = UnoRuntime.queryInterface(XModel.class,
					xSpreadsheetComponent);
			XController xSpreadsheetController = xSpreadsheetModel
					.getCurrentController();
			XSpreadsheetView xSpreadsheetView = UnoRuntime.queryInterface(
					XSpreadsheetView.class, xSpreadsheetController);
			xSpreadsheetView.setActiveSheet(xSpreadsheet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	private static void setHeaders(final XSpreadsheet sheet)
			throws IndexOutOfBoundsException {
		for (Map.Entry<GropinColumns, String> header : headers.entrySet()) {
			int columnNumber = header.getKey().ordinal();
			String columnName = header.getValue();
			sheet.getCellByPosition(columnNumber, 0).setFormula(columnName);
		}
	}

	private static void setModels(final XSpreadsheet sheet)
			throws CombineArchiveException, IndexOutOfBoundsException {
		try {

			List<PrimaryModelWData> models = PrimaryModelWDataFile
					.readPMFX("D:\\libreoffice-ws\\FirstSteps\\case1a.pmfx");

			for (short numModel = 0; numModel < models.size(); numModel++) {

				GropinModel gropinModel = createGropinModel(models.get(0),
						numModel);

				// Sets data in spreadsheet
				addModelToSheet(sheet, gropinModel, numModel + 1);
			}
		} catch (CombineArchiveException e) {
			e.printStackTrace();
		}
	}

	private static GropinModel createGropinModel(final PrimaryModelWData pmwd,
			short modelNumber) {
		Model currModel = pmwd.getModelDoc().getModel();

		GropinModel gropinModel = new GropinModel();
		gropinModel.setModelNumber(modelNumber);

		// Gets data from PMF
		// TODO: model type

		ModelRule rule = new ModelRule((AssignmentRule) currModel.getRule(0));
		Reference[] references = rule.getReferences();
		if (references != null) {
			Reference ref0 = references[0];

			if (ref0.isSetAuthor())
				gropinModel.setAuthors(ref0.getAuthor());

			if (ref0.isSetTitle())
				gropinModel.setPaper(ref0.getTitle());

			if (ref0.isSetJournal())
				gropinModel.setJournal(ref0.getJournal());

			if (ref0.isSetIssue())
				gropinModel.setIssue(ref0.getIssue());

			if (ref0.isSetAuthor() && ref0.isSetYear())
				gropinModel.setAuthorAndYear(ref0.getAuthor() + ", "
						+ ref0.getYear());
		}

		PMFSpecies species = SBMLFactory.createPMFSpecies(currModel
				.getSpecies(0));
		if (species.getSpecies().isSetName())
			gropinModel.setMicroorganisms(species.getSpecies().getName());

		PMFCompartment compartment = SBMLFactory.createPMFCompartment(currModel
				.getCompartment(0));
		if (compartment.getCompartment().isSetName()) {
			gropinModel.setSubstrate(compartment.getCompartment().getName());
			gropinModel.setProduct(compartment.getCompartment().getName());
		}

		return gropinModel;
	}

	private static void addModelToSheet(final XSpreadsheet sheet,
			final GropinModel model, int rowNumber)
			throws IndexOutOfBoundsException {

		if (model.isSetGrowthNoGrowth())
			sheet.getCellByPosition(GropinColumns.model_type.ordinal(),
					rowNumber).setFormula(model.getGrowthNoGrowth());

		if (model.isSetModelNumber())
			sheet.getCellByPosition(
					GropinColumns.sequential_numbering.ordinal(), rowNumber)
					.setValue(model.getModelNumber());

		if (model.isSetSubstrate())
			sheet.getCellByPosition(GropinColumns.substrate.ordinal(),
					rowNumber).setFormula(model.getSubstrate());

		if (model.isSetAuthors())
			sheet.getCellByPosition(GropinColumns.authors.ordinal(), rowNumber)
					.setFormula(model.getAuthors());

		if (model.isSetPaper())
			sheet.getCellByPosition(GropinColumns.paper.ordinal(), rowNumber)
					.setFormula(model.getPaper());

		if (model.isSetJournal())
			sheet.getCellByPosition(GropinColumns.journal.ordinal(), rowNumber)
					.setFormula(model.getJournal());

		if (model.isSetIssue())
			sheet.getCellByPosition(GropinColumns.issue.ordinal(), rowNumber)
					.setFormula(model.getIssue());

		if (model.isSetMicroorganisms())
			sheet.getCellByPosition(GropinColumns.microorganisms.ordinal(),
					rowNumber).setFormula(model.getMicroorganisms());

		if (model.isSetAuthorAndYear())
			sheet.getCellByPosition(
					GropinColumns.first_author_and_year.ordinal(), rowNumber)
					.setFormula(model.getAuthorAndYear());

		if (model.isSetProduct())
			sheet.getCellByPosition(GropinColumns.product.ordinal(), rowNumber)
					.setFormula(model.getProduct());

		if (model.isSetPsicheck())
			sheet.getCellByPosition(GropinColumns.psicheck.ordinal(), rowNumber)
					.setFormula(model.getPsicheck());

		if (model.isSetModelCateg())
			sheet.getCellByPosition(GropinColumns.model_categ.ordinal(),
					rowNumber).setFormula(model.getModelCateg());

		if (model.isSetIntegrated())
			sheet.getCellByPosition(GropinColumns.integrated.ordinal(),
					rowNumber).setFormula(model.getIntegrated());

		if (model.isSetMumaxUn())
			sheet.getCellByPosition(GropinColumns.mumax_un.ordinal(), rowNumber)
					.setFormula(model.getMumaxUn());

		if (model.isSetRateLabel())
			sheet.getCellByPosition(GropinColumns.rate_label.ordinal(),
					rowNumber).setFormula(model.getRateLabel());

		if (model.isSetSpecialNotes())
			sheet.getCellByPosition(GropinColumns.special_notes.ordinal(),
					rowNumber).setFormula(model.getSpecialNotes());

		if (model.isSetReferenceEquation())
			sheet.getCellByPosition(GropinColumns.reference_equation.ordinal(),
					rowNumber).setFormula(model.getReferenceEquation());

		if (model.isSetPossibleSimulations())
			sheet.getCellByPosition(
					GropinColumns.possible_simulations.ordinal(), rowNumber)
					.setFormula(model.getPossibleSimulations());

		if (model.isSetRateSolutionFormat())
			sheet.getCellByPosition(
					GropinColumns.rate_solution_format.ordinal(), rowNumber)
					.setFormula(model.getRateSolutionFormat());

		if (model.isSetEquation())
			sheet.getCellByPosition(GropinColumns.equation.ordinal(), rowNumber)
					.setFormula(model.getEquation());
	}

	static class GropinModel {

		/** Growth or No Growth model. */
		private String growthNoGrowth;

		/** Model sequential numbering. */
		private Short modelNumber;

		private String substrate;

		/** Authors of model's paper. */
		private String authors;

		/** Model's paper. */
		private String paper;

		/** Journal. */
		private String journal;

		/** Journal issue. */
		private String issue;

		private String inactive;

		/** Microorganisms. */
		private String microorganisms;

		private String authorAndYear;

		private String product;

		private String psicheck;

		private String modelCateg;

		private String integrated;

		private String mumaxUn;

		private String rateLabel;

		private String specialNotes;

		private String referenceEquation;

		/** Cases of possible simulations. */
		private String possibleSimulations;

		/** format of the rate solution. */
		private String rateSolutionFormat;

		/** Equation. */
		private String equation;

		// --- growthNoGrowth ---
		public String getGrowthNoGrowth() {
			if (growthNoGrowth == null)
				throw new RuntimeException("growthNoGrowth not set");
			return growthNoGrowth;
		}

		public void setGrowthNoGrowth(final String growthNoGrowth) {
			this.growthNoGrowth = growthNoGrowth;
		}

		public void unsetGrowthNoGrowth() {
			growthNoGrowth = null;
		}

		public boolean isSetGrowthNoGrowth() {
			return growthNoGrowth != null;
		}

		// --- Model number ---
		public short getModelNumber() {
			if (modelNumber == null)
				throw new RuntimeException("Model number is not set");
			return modelNumber;
		}

		public void setModelNumber(final short modelNumber) {
			this.modelNumber = modelNumber;
		}

		public void unsetModelNumber() {
			modelNumber = null;
		}

		public boolean isSetModelNumber() {
			return modelNumber != null;
		}

		// --- substrate ---
		public String getSubstrate() {
			if (substrate == null)
				throw new RuntimeException("Substrate is not set");
			return substrate;
		}

		public void setSubstrate(final String substrate) {
			this.substrate = substrate;
		}

		public void unsetSubstrate() {
			substrate = null;
		}

		public boolean isSetSubstrate() {
			return substrate != null;
		}

		// --- Authors of model's paper ---
		public String getAuthors() {
			if (authors == null)
				throw new RuntimeException("Authors of model's papers not set");
			return authors;
		}

		public void setAuthors(final String authors) {
			this.authors = authors;
		}

		public void unsetAuthors() {
			authors = null;
		}

		public boolean isSetAuthors() {
			return authors != null;
		}

		// --- Model's paper ---
		public String getPaper() {
			if (paper == null)
				throw new RuntimeException("Paper not set");
			return paper;
		}

		public void setPaper(final String paper) {
			this.paper = paper;
		}

		public void unsetPaper() {
			paper = null;
		}

		public boolean isSetPaper() {
			return paper != null;
		}

		// --- journal ---
		public String getJournal() {
			if (journal == null)
				throw new RuntimeException("Journal not set");
			return journal;
		}

		public void setJournal(final String journal) {
			this.journal = journal;
		}

		public void unsetJournal() {
			journal = null;
		}

		public boolean isSetJournal() {
			return journal != null;
		}

		// --- issue ---
		public String getIssue() {
			if (issue == null)
				throw new RuntimeException("Issue not set");
			return issue;
		}

		public void setIssue(final String issue) {
			this.issue = issue;
		}

		public void unsetIssue() {
			issue = null;
		}

		public boolean isSetIssue() {
			return issue != null;
		}

		// --- inactive ---
		public String getInactive() {
			if (inactive == null)
				throw new RuntimeException("Inactive not set");
			return inactive;
		}

		public void setInactive(final String inactive) {
			this.inactive = inactive;
		}

		public void unsetInactive() {
			inactive = null;
		}

		public boolean isSetInactive() {
			return inactive != null;
		}

		// --- microorganisms ---
		public String getMicroorganisms() {
			if (microorganisms == null)
				throw new RuntimeException("Microorganisms not set");
			return microorganisms;
		}

		public void setMicroorganisms(final String microorganisms) {
			this.microorganisms = microorganisms;
		}

		public void unsetMicroorganisms() {
			microorganisms = null;
		}

		public boolean isSetMicroorganisms() {
			return microorganisms != null;
		}

		// --- psicheck ---
		public String getPsicheck() {
			if (psicheck == null)
				throw new RuntimeException("psicheck not set");
			return psicheck;
		}

		public void setPsicheck(final String psicheck) {
			this.psicheck = psicheck;
		}

		public void unsetPsicheck() {
			psicheck = null;
		}

		public boolean isSetPsicheck() {
			return psicheck != null;
		}

		// --- product ---
		public String getProduct() {
			if (product == null)
				throw new RuntimeException("Product not set");
			return product;
		}

		public void setProduct(final String product) {
			this.product = product;
		}

		public void unsetProduct() {
			product = null;
		}

		public boolean isSetProduct() {
			return product != null;
		}

		// --- author and year ---
		public String getAuthorAndYear() {
			if (authorAndYear == null)
				throw new RuntimeException("Author and year not set");
			return authorAndYear;
		}

		public void setAuthorAndYear(final String authorAndYear) {
			this.authorAndYear = authorAndYear;
		}

		public void unsetAuthorAndYear() {
			authorAndYear = null;
		}

		public boolean isSetAuthorAndYear() {
			return authorAndYear != null;
		}

		// --- modelCateg ---
		public String getModelCateg() {
			if (modelCateg == null)
				throw new RuntimeException("modelCateg not set");
			return modelCateg;
		}

		public void setModelCateg(final String modelCateg) {
			this.modelCateg = modelCateg;
		}

		public void unsetModelCateg() {
			modelCateg = null;
		}

		public boolean isSetModelCateg() {
			return modelCateg != null;
		}

		// --- integrated ---
		public String getIntegrated() {
			if (integrated == null)
				throw new RuntimeException("integrated not set");
			return integrated;
		}

		public void setIntegrated(final String integrated) {
			this.integrated = integrated;
		}

		public void unsetIntegrated() {
			integrated = null;
		}

		public boolean isSetIntegrated() {
			return integrated != null;
		}

		// --- mumaxUn ---
		public String getMumaxUn() {
			if (mumaxUn == null)
				throw new RuntimeException("mumaxUn not set");
			return mumaxUn;
		}

		public void setMumaxUn(final String mumaxUn) {
			this.mumaxUn = mumaxUn;
		}

		public void unsetMumaxUn() {
			mumaxUn = null;
		}

		public boolean isSetMumaxUn() {
			return mumaxUn != null;
		}

		// --- rate label ---
		public String getRateLabel() {
			if (rateLabel == null)
				throw new RuntimeException("rate label not set");
			return rateLabel;
		}

		public void setRateLabel(final String rateLabel) {
			this.rateLabel = rateLabel;
		}

		public void unsetRateLabel() {
			rateLabel = null;
		}

		public boolean isSetRateLabel() {
			return rateLabel != null;
		}

		// --- special notes ---
		public String getSpecialNotes() {
			if (specialNotes == null)
				throw new RuntimeException("special notes not set");
			return specialNotes;
		}

		public void setSpecialNotes(final String specialNotes) {
			this.specialNotes = specialNotes;
		}

		public void unsetSpecialNotes() {
			specialNotes = null;
		}

		public boolean isSetSpecialNotes() {
			return specialNotes != null;
		}

		// --- reference equation ---
		public String getReferenceEquation() {
			if (referenceEquation == null)
				throw new RuntimeException("reference equation not set");
			return referenceEquation;
		}

		public void setReferenceEquation(final String referenceEquation) {
			this.referenceEquation = referenceEquation;
		}

		public void unsetReferenceEquation() {
			referenceEquation = null;
		}

		public boolean isSetReferenceEquation() {
			return referenceEquation != null;
		}

		// --- cases of possible simulations ---
		public String getPossibleSimulations() {
			if (possibleSimulations == null)
				throw new RuntimeException("possible simulations not set");
			return possibleSimulations;
		}

		public void setPossibleSimulations(final String possibleSimulations) {
			this.possibleSimulations = possibleSimulations;
		}

		public void unsetPossibleSimulations() {
			possibleSimulations = null;
		}

		public boolean isSetPossibleSimulations() {
			return possibleSimulations != null;
		}

		// --- format of the rate solution ---
		public String getRateSolutionFormat() {
			if (rateSolutionFormat == null)
				throw new RuntimeException("rate solution format not set");
			return rateSolutionFormat;
		}

		public void setRateSolutionFormat(final String rateSolutionFormat) {
			this.rateSolutionFormat = rateSolutionFormat;
		}

		public void unsetRateSolutionFormat() {
			rateSolutionFormat = null;
		}

		public boolean isSetRateSolutionFormat() {
			return rateSolutionFormat != null;
		}

		// --- equation ---
		public String getEquation() {
			if (equation == null)
				throw new RuntimeException("equation not set");
			return equation;
		}

		public void setEquation(final String equation) {
			this.equation = equation;
		}

		public void unsetEquation() {
			equation = null;
		}

		public boolean isSetEquation() {
			return equation != null;
		}
	}
}
