package com.microsoft.applicationinsights.smoketestapp;

import com.microsoft.applicationinsights.internal.schemav2.Data;
import com.microsoft.applicationinsights.internal.schemav2.Envelope;
import com.microsoft.applicationinsights.internal.schemav2.RemoteDependencyData;
import com.microsoft.applicationinsights.internal.schemav2.RequestData;
import com.microsoft.applicationinsights.smoketest.AiSmokeTest;
import com.microsoft.applicationinsights.smoketest.TargetUri;
import com.microsoft.applicationinsights.smoketest.UseAgent;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@UseAgent
public class HttpClientSmokeTest extends AiSmokeTest {

    @Test
    @TargetUri("/apacheHttpClient4")
    public void testAsyncDependencyCallWithApacheHttpClient4() throws Exception {
        List<Envelope> rdList = mockedIngestion.waitForItems("RequestData", 1);
        List<Envelope> rddList = mockedIngestion.waitForItems("RemoteDependencyData", 1);

        Envelope rdEnvelope = rdList.get(0);
        Envelope rddEnvelope = rddList.get(0);

        RequestData rd = (RequestData) ((Data) rdEnvelope.getData()).getBaseData();
        RemoteDependencyData rdd = (RemoteDependencyData) ((Data) rddEnvelope.getData()).getBaseData();

        assertTrue(rd.getSuccess());
        assertEquals("GET /", rdd.getName());
        assertEquals("www.bing.com", rdd.getTarget());
        assertParentChild(rd, rdEnvelope, rddEnvelope);
    }

    @Test
    @TargetUri("/apacheHttpClient4WithResponseHandler")
    public void testAsyncDependencyCallWithApacheHttpClient4WithResponseHandler() throws Exception {
        List<Envelope> rdList = mockedIngestion.waitForItems("RequestData", 1);
        List<Envelope> rddList = mockedIngestion.waitForItems("RemoteDependencyData", 1);

        Envelope rdEnvelope = rdList.get(0);
        Envelope rddEnvelope = rddList.get(0);

        RequestData rd = (RequestData) ((Data) rdEnvelope.getData()).getBaseData();
        RemoteDependencyData rdd = (RemoteDependencyData) ((Data) rddEnvelope.getData()).getBaseData();

        assertTrue(rd.getSuccess());
        assertEquals("GET /", rdd.getName());
        assertEquals("www.bing.com", rdd.getTarget());
        assertParentChild(rd, rdEnvelope, rddEnvelope);
    }

    @Test
    @TargetUri("/apacheHttpClient3")
    public void testAsyncDependencyCallWithApacheHttpClient3() throws Exception {
        List<Envelope> rdList = mockedIngestion.waitForItems("RequestData", 1);
        List<Envelope> rddList = mockedIngestion.waitForItems("RemoteDependencyData", 1);

        Envelope rdEnvelope = rdList.get(0);
        Envelope rddEnvelope = rddList.get(0);

        RequestData rd = (RequestData) ((Data) rdEnvelope.getData()).getBaseData();
        RemoteDependencyData rdd = (RemoteDependencyData) ((Data) rddEnvelope.getData()).getBaseData();

        assertTrue(rd.getSuccess());
        assertEquals("GET /", rdd.getName());
        assertEquals("www.bing.com", rdd.getTarget());
        assertParentChild(rd, rdEnvelope, rddEnvelope);
    }

    @Test
    @TargetUri("/okHttp3")
    public void testAsyncDependencyCallWithOkHttp3() throws Exception {
        List<Envelope> rdList = mockedIngestion.waitForItems("RequestData", 1);
        List<Envelope> rddList = mockedIngestion.waitForItems("RemoteDependencyData", 1);

        Envelope rdEnvelope = rdList.get(0);
        Envelope rddEnvelope = rddList.get(0);

        RequestData rd = (RequestData) ((Data) rdEnvelope.getData()).getBaseData();
        RemoteDependencyData rdd = (RemoteDependencyData) ((Data) rddEnvelope.getData()).getBaseData();

        assertTrue(rd.getSuccess());
        assertEquals("GET /", rdd.getName());
        assertEquals("www.bing.com", rdd.getTarget());
        assertParentChild(rd, rdEnvelope, rddEnvelope);
    }

    @Test
    @TargetUri("/okHttp2")
    public void testAsyncDependencyCallWithOkHttp2() throws Exception {
        List<Envelope> rdList = mockedIngestion.waitForItems("RequestData", 1);
        List<Envelope> rddList = mockedIngestion.waitForItems("RemoteDependencyData", 1);

        Envelope rdEnvelope = rdList.get(0);
        Envelope rddEnvelope = rddList.get(0);

        RequestData rd = (RequestData) ((Data) rdEnvelope.getData()).getBaseData();
        RemoteDependencyData rdd = (RemoteDependencyData) ((Data) rddEnvelope.getData()).getBaseData();

        assertTrue(rd.getSuccess());
        assertEquals("GET /", rdd.getName());
        assertEquals("www.bing.com", rdd.getTarget());
        assertParentChild(rd, rdEnvelope, rddEnvelope);
    }

    @Test
    @TargetUri("/httpURLConnection")
    public void testAsyncDependencyCallWithHttpURLConnection() throws Exception {
        List<Envelope> rdList = mockedIngestion.waitForItems("RequestData", 1);
        List<Envelope> rddList = mockedIngestion.waitForItems("RemoteDependencyData", 1);

        Envelope rdEnvelope = rdList.get(0);
        Envelope rddEnvelope = rddList.get(0);

        RequestData rd = (RequestData) ((Data) rdEnvelope.getData()).getBaseData();
        RemoteDependencyData rdd = (RemoteDependencyData) ((Data) rddEnvelope.getData()).getBaseData();

        assertTrue(rd.getSuccess());
        assertEquals("GET /", rdd.getName());
        assertEquals("www.bing.com", rdd.getTarget());
        assertParentChild(rd, rdEnvelope, rddEnvelope);
    }

    private static void assertParentChild(RequestData rd, Envelope rdEnvelope, Envelope rddEnvelope) {
        String operationId = rdEnvelope.getTags().get("ai.operation.id");

        assertNotNull(operationId);

        assertEquals(operationId, rddEnvelope.getTags().get("ai.operation.id"));
        assertEquals(rd.getId(), rddEnvelope.getTags().get("ai.operation.parentId"));
    }
}
